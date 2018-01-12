package com.hason.dtp.account.service.client;

import com.hason.dtp.message.api.MessageApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 可靠消息微服务客户端
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/23
 */
@FeignClient(name = "rm-message")
public interface MessageServiceClient extends MessageApi {
}
