package com.hason.dtp.tcc.capital.controller;

import com.hason.dtp.core.support.tcc.TransactionEntity;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.core.utils.result.ResultBuilder;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.capital.api.OrderApi;
import com.hason.dtp.tcc.capital.dto.CreateOrderDto;
import com.hason.dtp.tcc.capital.entity.CapitalOrder;
import com.hason.dtp.tcc.capital.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * 订单 Controller
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/13
 */
@RestController
public class OrderController implements OrderApi {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/capitals/orders",
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Override
    public Result<CapitalOrder> create(@RequestBody CreateOrderDto<User> entity) {
        return ResultBuilder.newInstance(orderService.create(entity.getData(), entity.getAmont()));
    }
}
