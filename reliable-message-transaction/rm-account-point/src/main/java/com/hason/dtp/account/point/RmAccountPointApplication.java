package com.hason.dtp.account.point;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 账户积分
 *
 * （为了演示分布式程序，这里积分操作独立出一个项目）
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/15
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class RmAccountPointApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmAccountPointApplication.class, args);
    }

}
