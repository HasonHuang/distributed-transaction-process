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

    /**
     * (TCC-try) 创建积分账户
     *
     * @param context 事务上下文
     * @param user 用户
     * @return Point
     */
    Point create(TransactionContext context, User user);

    /**
     * (TCC-confirm) 确认创建积分账户，仅供框架自动调用
     *
     * @param context 事务上下文
     * @param user 用户
     * @return Point
     */
    void confirmCreate(TransactionContext context, User user);

    /**
     * (TCC-cancel) 撤销创建积分账户，仅供框架自动调用
     *
     * @param context 事务上下文
     * @param user 用户
     * @return Point
     */
    void cancelCreate(TransactionContext context, User user);


    /**
     * (TCC-try) 增加积分
     *
     * @param context 事务上下文
     * @param userId 用户ID
     * @param increment 增量
     */
    void incr(TransactionContext context, Long userId, Long increment);

    /**
     * (TCC-confirm) 确认增加积分，仅供框架自动调用
     *
     * @param context 事务上下文
     * @param userId 用户ID
     * @param increment 增量
     */
    void confirmIncr(TransactionContext context, Long userId, Long increment);

    /**
     * (TCC-cancel) 撤销增加积分，仅供框架自动调用
     *
     * @param context 事务上下文
     * @param userId 用户ID
     * @param increment 增量
     */
    void cancelIncr(TransactionContext context, Long userId, Long increment);

}
