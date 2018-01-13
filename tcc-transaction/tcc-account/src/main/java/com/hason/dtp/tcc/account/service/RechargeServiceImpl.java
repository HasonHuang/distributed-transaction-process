package com.hason.dtp.tcc.account.service;

import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.account.service.client.CapitalOrderServiceClient;
import com.hason.dtp.tcc.account.service.client.proxy.CapitalServiceClientProxy;
import com.hason.dtp.tcc.capital.dto.CreateOrderDto;
import com.hason.dtp.tcc.capital.entity.CapitalOrder;
import org.mengyun.tcctransaction.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.hason.dtp.core.utils.CheckUtil.notFalse;

/**
 * 充值业务实现类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/13
 */
@Service
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    private CapitalServiceClientProxy capitalServiceClientProxy;

    @Autowired
    private CapitalOrderServiceClient capitalOrderServiceClient;

    @Compensable(confirmMethod = "confirmRecharge", cancelMethod = "cancelRecharge")
    @Override
    public Result<?> recharge(CapitalOrder order) {
        // 为了演示嵌套事务，从tcc-account项目开始事务
        Result<?> rechargeResult = capitalServiceClientProxy.recharge(null, order);
        if (!rechargeResult.success()) {
            // 充值失败
            notFalse(rechargeResult.success(), "service.fail", rechargeResult.getError().getMessage());
        }
        return rechargeResult;
    }

    @Override
    public void confirmRecharge(CapitalOrder order) {
        // 无操作
    }

    @Override
    public void cancelRecharge(CapitalOrder order) {
        // 无操作
    }
}
