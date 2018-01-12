package com.hason.dtp.tcc.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 账户系统入口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/5
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class TccAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccAccountApplication.class, args);
    }

}
