package com.hason.dtp.tcc.account.service;

import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.account.dao.UserRepository;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.account.service.client.proxy.CapitalServiceClientProxy;
import com.hason.dtp.tcc.account.service.client.proxy.PointServiceClientProxy;
import com.hason.dtp.tcc.capital.entity.CapitalAccount;
import com.hason.dtp.tcc.integral.entity.Point;
import org.mengyun.tcctransaction.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.hason.dtp.core.utils.CheckUtil.notFalse;
import static com.hason.dtp.core.utils.CheckUtil.notNull;

/**
 * 用户业务实现类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/9
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private PointServiceClientProxy pointServiceClientProxy;
    @Autowired
    private CapitalServiceClientProxy capitalServiceClientProxy;

    @Autowired
    private UserRepository userRepository;

    @Compensable(confirmMethod = "confirmRegister", cancelMethod = "cancelRegister")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public User register(User user) {
        notNull(user, "arg.null", "用户对象");
        notNull(user.getUsername(), "arg.null", "用户名");

        User exists = get(user.getUsername());
        notFalse(exists == null, "arg.illegal", "用户名");

        user.setCreateTime(LocalDateTime.now());
        user.setModifiedTime(user.getCreateTime());
        user = save(user);

        // 注册积分账户，如果失败则抛出错误
        Result<Point> pointResult = pointServiceClientProxy.create(null, user);
        if (!pointResult.success()) {
            notFalse(pointResult.success(), "service.fail", pointResult.getError().getMessage());
        }

        // 注册资金账户，如果失败则抛出错误
        Result<CapitalAccount> capitalResult = capitalServiceClientProxy.create(null, user);
        if (!capitalResult.success()) {
            notFalse(capitalResult.success(), "service.fail", pointResult.getError().getMessage());
        }

        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User confirmRegister(User user) {
        LOGGER.debug("开始确认用户[{}]注册信息", user.getUsername());
        // 实现幂等性
        // 已经插入记录了，确认方法无需做任何事情
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User cancelRegister(User user) {
        LOGGER.debug("开始取消用户[{}]注册信息", user.getUsername());

        // 如果 User 没有id，表示还未保存到数据库，无需删除用户
        if (user.getId() == null) {
            return user;
        }

        // 实现幂等性
        User exist = get(user.getUsername());
        if (exist != null) {
            userRepository.delete(exist.getId());
        }

        return exist;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User save(User user) {
        notNull(user, "arg.null", "用户对象");
        notNull(user.getUsername(), "arg.null", "用户名");
        notNull(user.getPassword(), "arg.null", "用户密码");
        userRepository.save(user);
        return user;
    }

    @Override
    public User get(Long userId) {
        notNull(userId, "arg.null", "用户ID");
        return userRepository.findOne(userId);
    }

    @Override
    public User get(String username) {
        notNull(username, "arg.null", "用户名");
        return userRepository.findByUsername(username);
    }
}
