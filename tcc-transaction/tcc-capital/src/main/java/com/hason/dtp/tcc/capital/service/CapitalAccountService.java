package com.hason.dtp.tcc.capital.service;

import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.capital.entity.CapitalAccount;
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

}
