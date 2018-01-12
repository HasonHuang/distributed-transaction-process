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
    public Point confirmCreate(TransactionContext context, User user) {
        LOGGER.debug("开始确认用户[{}]积分信息", user.getUsername());
        // 实现幂等性
        // 已经插入记录了，确认方法无需做任何事情
        return pointRepository.findByUserId(user.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Point cancelCreate(TransactionContext context, User user) {
        LOGGER.debug("开始取消用户[{}]积分信息", user.getUsername());
        // 实现幂等性
        Point point = pointRepository.findByUserId(user.getId());
        if (point != null) {
            pointRepository.delete(point.getId());
        }
        return point;
    }

}
