package com.hason.dtp.tcc.integral.service;

import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.integral.entity.Point;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * 积分业务接口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/8
 */
public interface PointService {

    Point create(TransactionContext context, User user);

    Point confirmCreate(TransactionContext context, User user);

    Point cancelCreate(TransactionContext context, User user);

}
