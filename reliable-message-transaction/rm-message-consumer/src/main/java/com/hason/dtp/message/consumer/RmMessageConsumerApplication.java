package com.hason.dtp.message.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 可靠消息服务 - 消息业务消费端
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/12
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class RmMessageConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmMessageConsumerApplication.class, args);
    }

}
