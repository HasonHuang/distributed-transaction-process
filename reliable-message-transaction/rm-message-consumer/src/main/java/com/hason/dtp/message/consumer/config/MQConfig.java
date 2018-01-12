package com.hason.dtp.message.consumer.config;

import com.hason.dtp.message.consumer.config.properties.QueueMessageProperties;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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
public class MQConfig {

    @Bean
    public Queue exampleQueue(QueueMessageProperties properties) {
        // 创建一个持久化的队列
        return new Queue(properties.getUserPointQueue(), true);
    }

    // 使用自定义的消息转换器，避免循环报异常：
    // org.springframework.amqp.AmqpException: No method found for class [B
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
//        return template;
//    }
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        // 设置确认模式为自动确认
//        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        return factory;
//    }
}
