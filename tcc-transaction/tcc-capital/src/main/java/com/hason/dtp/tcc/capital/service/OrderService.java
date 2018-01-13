package com.hason.dtp.tcc.capital.service;

import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.capital.entity.CapitalOrder;
import com.hason.dtp.tcc.capital.entity.constant.CapitalOrderStatus;

import java.math.BigDecimal;

/**
 * 订单业务类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/13
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param user 用户
     * @param amount 订单金额
     * @return CapitalOrder
     */
    CapitalOrder create(User user, BigDecimal amount);

    /**
     * 获取订单
     *
     * @param id 订单ID
     * @return CapitalOrder
     */
    CapitalOrder get(Long id);

    /**
     * 改变订单的状态
     *
     * @param id 订单ID
     * @param toStatus 新状态
     */
    CapitalOrder changeStatus(Long id, CapitalOrderStatus toStatus);

}
