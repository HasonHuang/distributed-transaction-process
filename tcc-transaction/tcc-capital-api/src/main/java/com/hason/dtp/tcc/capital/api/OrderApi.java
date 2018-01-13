package com.hason.dtp.tcc.capital.api;

import com.hason.dtp.core.support.tcc.TransactionEntity;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.capital.dto.CreateOrderDto;
import com.hason.dtp.tcc.capital.entity.CapitalAccount;
import com.hason.dtp.tcc.capital.entity.CapitalOrder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 订单 API
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/13
 */
public interface OrderApi {

    /**
     * 创建订单
     *
     * @param entity 订单参数
     * @return {@code Result<CapitalAccount>}
     */
    @RequestMapping(value = "/capitals/orders", method = POST,
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    Result<CapitalOrder> create(@RequestBody CreateOrderDto<User> entity);

}
