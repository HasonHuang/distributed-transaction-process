package com.hason.dtp.tcc.account.service;

import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.capital.entity.CapitalOrder;

import java.math.BigDecimal;

/**
 * 充值业务接口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/13
 */
public interface RechargeService {

    Result<?> recharge(CapitalOrder order);
    void confirmRecharge(CapitalOrder order);
    void cancelRecharge(CapitalOrder order);

}
