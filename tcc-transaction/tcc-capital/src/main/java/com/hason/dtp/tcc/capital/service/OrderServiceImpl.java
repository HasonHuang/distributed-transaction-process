package com.hason.dtp.tcc.capital.service;

import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.capital.dao.CapitalOrderRepository;
import com.hason.dtp.tcc.capital.entity.CapitalOrder;
import com.hason.dtp.tcc.capital.entity.constant.CapitalOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

import static com.hason.dtp.core.utils.CheckUtil.notFalse;
import static com.hason.dtp.core.utils.CheckUtil.notNull;

/**
 * 订单业务实现类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/13
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CapitalOrderRepository capitalOrderRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CapitalOrder create(User user, BigDecimal amount) {

        // 检验参数
        notNull(user, "arg.null", "用户");
        notNull(user.getId(), "arg.null", "用户ID");
        notNull(amount, "arg.null", "订单金额");
        notFalse(amount.compareTo(BigDecimal.ZERO) >= 0, "arg.illegal", "订单金额");

        CapitalOrder order = new CapitalOrder();
        order.setAmount(amount);
        order.setUserId(user.getId());
        // 这里随机生成商家订单号，而实际业务不可能随机生成
        order.setMerchantOrderNo(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setStatus(CapitalOrderStatus.CREATE);

        capitalOrderRepository.save(order);

        return order;
    }

    @Transactional(readOnly = true)
    @Override
    public CapitalOrder get(Long id) {
        return capitalOrderRepository.findOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CapitalOrder changeStatus(Long id, CapitalOrderStatus toStatus) {
        // 查找订单
        CapitalOrder order = capitalOrderRepository.findOne(id);
        notNull(order, "service.fail", "订单ID不存在:" + id);
        // 更新订单
        order.setStatus(toStatus);
        capitalOrderRepository.save(order);
        return order;
    }
}
