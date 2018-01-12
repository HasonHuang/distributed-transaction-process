package com.hason.dtp.account.point.service;

import com.hason.dtp.account.entity.User;
import com.hason.dtp.account.point.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hason.dtp.core.utils.CheckUtil.notNull;

/**
 * 积分业务实现类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/15
 */
@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addRegistPoint(Long userId, int add) {
        User user = userRepository.findOne(userId);
        notNull(user, "arg.illegal", "用户ID");

        // （实现幂等性）判断积分是否已经增加过，如果是直接返回。
        if (user.getPoint() > 0) {
            return;
        }

        user.setPoint(user.getPoint() + add);
        userRepository.save(user);
    }
}
