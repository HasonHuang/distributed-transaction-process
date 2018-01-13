package com.hason.dtp.tcc.capital.service.client.proxy;

import com.hason.dtp.core.support.tcc.TransactionEntity;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.capital.service.client.PointServiceClient;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 积分的微服务代理客户端
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/8
 */
@Component
public class PointServiceClientProxy {

    @Autowired
    private PointServiceClient pointServiceClient;

    /**
     * HTTP 需要多使用一个中间层调用远程服务，以便让事务拦截器记录参与者
     *
     * @param context
     * @param userId
     * @param increment
     * @return
     */
    public Result<?> incr(TransactionContext context, Long userId, Long increment) {
        return pointServiceClient.incr(userId, new TransactionEntity<>(context, increment));
    }
}
