package com.hason.dtp.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 账户微服务
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/17
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class RmAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmAccountApplication.class, args);
    }

}
