package com.hason.dtp.tcc.capital.service.client;

import com.hason.dtp.tcc.integral.api.PointApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 积分微服务客户端
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/8
 */
@FeignClient(name = "tcc-integral")
public interface PointServiceClient extends PointApi {
}
