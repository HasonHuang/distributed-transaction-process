package com.hason.dtp.message.config;

import com.hason.dtp.message.config.properties.QueueMessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/19
 */
@Configuration
//@ConfigurationProperties(prefix = "queue.message")
//@PropertySource(value = "classpath:application.yml")
public class MQConfig {

    @Bean
    public Queue exampleQueue(QueueMessageProperties properties) {
        // 创建一个持久化的队列
        return new Queue(properties.getUserPointQueue(), true);
    }

}
