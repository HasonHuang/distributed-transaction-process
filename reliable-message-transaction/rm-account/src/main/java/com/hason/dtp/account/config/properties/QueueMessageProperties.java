package com.hason.dtp.account.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 消息队列的属性配置
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/7
 */
@Configuration
@ConfigurationProperties(prefix = "queue.message")
@PropertySource(value = "classpath:application.yml")
public class QueueMessageProperties {

    private String userPointQueue;

    public String getUserPointQueue() {
        return userPointQueue;
    }

    public void setUserPointQueue(String userPointQueue) {
        this.userPointQueue = userPointQueue;
    }

}
