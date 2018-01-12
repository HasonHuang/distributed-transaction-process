package com.hason.dtp.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 可靠消息服务程序入口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/23
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class RmMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmMessageApplication.class, args);
    }

}
