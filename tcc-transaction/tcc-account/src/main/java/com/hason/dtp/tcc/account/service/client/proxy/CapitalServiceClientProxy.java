package com.hason.dtp.tcc.account.service.client.proxy;

import com.hason.dtp.core.support.tcc.TransactionEntity;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.account.service.client.CapitalAccountServiceClient;
import com.hason.dtp.tcc.capital.entity.CapitalAccount;
import com.hason.dtp.tcc.capital.entity.CapitalOrder;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 资金账户的服务代理客户端
 *
 * HTTP 需要多使用一个中间层调用远程服务，以便让事务拦截器记录参与者
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/12
 */
@Component
public class CapitalServiceClientProxy {

    @Autowired
    private CapitalAccountServiceClient capitalAccountServiceClient;

    /**
     * 创建资金账户
     */
    public Result<CapitalAccount> create(TransactionContext context, User user) {
        return capitalAccountServiceClient.create(new TransactionEntity<>(context, user));
    }

    /**
     * 充值
     */
    public Result<?> recharge(TransactionContext context, CapitalOrder order) {
        return capitalAccountServiceClient.recharge(new TransactionEntity<>(context, order));
    }

}
