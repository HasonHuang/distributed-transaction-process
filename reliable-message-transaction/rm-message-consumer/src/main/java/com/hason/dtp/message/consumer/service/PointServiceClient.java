package com.hason.dtp.message.consumer.service;

import com.hason.dtp.account.point.api.PointApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 积分微服务客户端
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/15
 */
@FeignClient(name = "rm-account-point")
public interface PointServiceClient extends PointApi {
}
