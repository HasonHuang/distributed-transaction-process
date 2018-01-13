package com.hason.dtp.tcc.capital.service;

import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.capital.entity.CapitalAccount;
import com.hason.dtp.tcc.capital.entity.CapitalOrder;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * 资金账户业务接口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/12
 */
public interface CapitalAccountService {

    /**
     * (TCC-try) 创建资金账户
     *
     * @param context 事务上下文
     * @param user 用户
     * @return CapitalAccount
     */
    CapitalAccount create(TransactionContext context, User user);

    /**
     * (TCC-confirm) 确认创建资金账户，仅供框架自动调用
     *
     * @param context 事务上下文
     * @param user 用户
     * @return CapitalAccount
     */
    CapitalAccount confirmCreate(TransactionContext context, User user);

    /**
     * (TCC-cancel) 撤销创建资金账户，仅供框架自动调用
     *
     * @param context 事务上下文
     * @param user 用户
     * @return CapitalAccount
     */
    CapitalAccount cancelCreate(TransactionContext context, User user);


    /**
     * (TCC-try) 用户充值
     *
     * @param context 事务上下文
     * @param order 资金账户订单
     * @return CapitalAccount
     */
    void recharge(TransactionContext context, CapitalOrder order);

    /**
     * (TCC-confirm) 确认用户充值，仅供框架自动调用
     *
     * @param context 事务上下文
     * @param order 资金账户订单
     * @return CapitalAccount
     */
    void confirmRecharge(TransactionContext context, CapitalOrder order);

    /**
     * (TCC-cancel) 撤销用户充值，仅供框架自动调用
     *
     * @param context 事务上下文
     * @param order 资金账户订单
     * @return CapitalAccount
     */
    void cancelRecharge(TransactionContext context, CapitalOrder order);

}
