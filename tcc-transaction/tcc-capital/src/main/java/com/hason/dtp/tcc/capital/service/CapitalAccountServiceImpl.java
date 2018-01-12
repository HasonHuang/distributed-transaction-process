package com.hason.dtp.tcc.capital.service;

import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.capital.dao.CapitalAccountRepository;
import com.hason.dtp.tcc.capital.entity.CapitalAccount;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.hason.dtp.core.utils.CheckUtil.notNull;

/**
 * 资金账户业务实现类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/12
 */
@Service
public class CapitalAccountServiceImpl implements CapitalAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CapitalAccountServiceImpl.class);

    @Autowired
    private CapitalAccountRepository capitalAccountRepository;

    @Compensable(confirmMethod = "confirmCreate", cancelMethod = "cancelCreate")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CapitalAccount create(TransactionContext context, User user) {

        // 显式的事务上下文参数是框架的要求，在业务中无需用到，处理业务时忽略掉

        // 检验参数
        notNull(user, "arg.null", "用户");
        notNull(user.getId(), "arg.null", "用户");

        // 创建资金账户
        CapitalAccount account = new CapitalAccount();
        account.setBalanceAmount(BigDecimal.ZERO);
        account.setUserId(user.getId());
        capitalAccountRepository.save(account);

        return account;
    }

    @Transactional(readOnly = true)
    @Override
    public CapitalAccount confirmCreate(TransactionContext context, User user) {
        LOGGER.debug("开始确认用户[{}]资金信息", user.getUsername());
        // 实现幂等性
        // 已经插入记录了，确认方法无需做任何事情
        return capitalAccountRepository.findByUserId(user.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CapitalAccount cancelCreate(TransactionContext context, User user) {
        LOGGER.debug("开始取消用户[{}]资金信息", user.getUsername());
        // 实现幂等性
        CapitalAccount account = capitalAccountRepository.findByUserId(user.getId());
        if (account != null) {
            LOGGER.debug("删除资金账户[{}]", account.getId());
            capitalAccountRepository.delete(account.getId());
        }
        return account;
    }

}
