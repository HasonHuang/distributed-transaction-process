package com.hason.dtp.tcc.account.service.client;

import com.hason.dtp.tcc.capital.api.OrderApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 资金订单微服务客户端
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/13
 */
@FeignClient(name = "tcc-capital")
public interface CapitalOrderServiceClient extends OrderApi {
}
