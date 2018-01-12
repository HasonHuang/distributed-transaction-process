package com.hason.dtp.test.message.consumer.sender;

import com.hason.dtp.message.consumer.config.properties.QueueMessageProperties;
import com.hason.dtp.test.message.consumer.BaseTest;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 发送测试
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/12
 */
public class SendTest extends BaseTest {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private QueueMessageProperties properties;

    @Test
    public void testSend() {
        amqpTemplate.convertAndSend(properties.getUserPointQueue(), "单元测试");
    }

}
