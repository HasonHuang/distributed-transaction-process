package com.hason.dtp.tcc.account.service.client;

import com.hason.dtp.tcc.capital.api.CapitalApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 资金账户微服务客户端
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/12
 */
@FeignClient(name = "tcc-capital")
public interface CapitalAccountServiceClient extends CapitalApi {
}
