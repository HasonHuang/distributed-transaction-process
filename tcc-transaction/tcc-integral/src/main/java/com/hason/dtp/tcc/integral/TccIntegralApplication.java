package com.hason.dtp.tcc.integral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 积分系统程序入口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/8
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TccIntegralApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccIntegralApplication.class, args);
    }

}
