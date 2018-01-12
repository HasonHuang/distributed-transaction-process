package com.hason.dtp.message.consumer.service;

import com.hason.dtp.message.api.MessageApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 可靠消息微服务客户端
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/12
 */
@FeignClient(name = "rm-message")
public interface MessageServiceClient extends MessageApi {
}
