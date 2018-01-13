package com.hason.dtp.tcc.integral.service;

import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.integral.dao.PointRepository;
import com.hason.dtp.tcc.integral.entity.Point;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hason.dtp.core.utils.CheckUtil.notNull;

/**
 * 积分业务实现类，创建用户的积分账户
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/8
 */
@Service
public class PointServiceImpl implements PointService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointServiceImpl.class);

    @Autowired
    private PointRepository pointRepository;

    @Compensable(confirmMethod = "confirmCreate", cancelMethod = "cancelCreate")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Point create(TransactionContext context, User user) {
        notNull(user, "arg.null", "用户");
        notNull(user.getId(), "arg.null", "用户");

        Point point = new Point();
        point.setUserId(user.getId());
        point.setValue(0L);
        pointRepository.save(point);

        return point;
    }

    @Transactional(readOnly = true)
    @Override
    public void confirmCreate(TransactionContext context, User user) {
        LOGGER.debug("开始确认用户[{}]积分信息", user.getUsername());
        // 实现幂等性
        // 已经插入记录了，确认方法无需做任何事情
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelCreate(TransactionContext context, User user) {
        LOGGER.debug("开始取消用户[{}]积分信息", user.getUsername());
        // 实现幂等性
        Point point = pointRepository.findByUserId(user.getId());
        if (point != null) {
            pointRepository.delete(point.getId());
        }
    }

    @Compensable(confirmMethod = "confirmIncr", cancelMethod = "cancelIncr")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void incr(TransactionContext context, Long userId, Long increment) {
        notNull(userId, "arg.null", "用户ID");
        notNull(increment, "arg.null", "积分增量");

        Point point = pointRepository.findByUserId(userId);
        notNull(point, "service.fail", "用户不存在积分账户");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void confirmIncr(TransactionContext context, Long userId, Long increment) {
        LOGGER.debug("开始确认用户[{}]的积分增量[{}]", userId, increment);

        // TCC 操作中，参数检验的步骤在 try 阶段执行，其他阶段不再检验
        Point point = pointRepository.findByUserId(userId);
        // 在实际业务中，注意并发问题
        point.setValue(point.getValue() + increment);
        pointRepository.save(point);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelIncr(TransactionContext context, Long userId, Long increment) {
        // try操作并没有修改东西，撤销操作无需任何操作
    }
}
