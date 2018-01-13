package com.hason.dtp.tcc.account.service.client.proxy;

import com.hason.dtp.core.support.tcc.TransactionEntity;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.account.service.client.PointServiceClient;
import com.hason.dtp.tcc.integral.entity.Point;
import org.mengyun.tcctransaction.Compensable;
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
     * @param user
     * @return
     */
    public Result<Point> create(TransactionContext context, User user) {
        return pointServiceClient.create(new TransactionEntity<>(context, user));
    }
}
