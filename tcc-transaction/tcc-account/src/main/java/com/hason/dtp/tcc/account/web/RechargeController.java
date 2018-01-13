package com.hason.dtp.tcc.account.web;

import com.hason.dtp.core.support.MediaTypes;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.account.service.RechargeService;
import com.hason.dtp.tcc.account.service.UserService;
import com.hason.dtp.tcc.account.service.client.CapitalOrderServiceClient;
import com.hason.dtp.tcc.capital.dto.CreateOrderDto;
import com.hason.dtp.tcc.capital.entity.CapitalOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

import static com.hason.dtp.core.support.MediaTypes.JSON;
import static com.hason.dtp.core.utils.CheckUtil.notFalse;
import static com.hason.dtp.core.utils.CheckUtil.notNull;

/**
 * 充值 Controller
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/13
 */
@Controller
public class RechargeController {

    @Autowired
    private CapitalOrderServiceClient capitalOrderServiceClient;

    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/recharge")
    public String page() {
        return "recharge";
    }

    @PostMapping(value = "/capitals/balance", produces = JSON)
    @ResponseBody
    public Result<?> recharge(String username, BigDecimal amount) {

        // 查找用户
        User user = userService.get(username);
        notNull(user, "service.fail", "找不到用户:" + username);

        // 创建订单
        CreateOrderDto<User> dto = new CreateOrderDto<>();
        dto.setData(user);
        dto.setAmont(amount);
        Result<CapitalOrder> orderResult = capitalOrderServiceClient.create(dto);
        if (!orderResult.success()) {
            // 创建订单失败
            notFalse(orderResult.success(), "service.fail", orderResult.getError().getMessage());
        }

        // （简单演示，省略了支付步骤）

        // 充值
        return rechargeService.recharge(orderResult.getData());
    }

}
