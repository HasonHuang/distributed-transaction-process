package com.hason.dtp.test.message.service;

import com.hason.dtp.core.utils.JsonMapper;
import com.hason.dtp.message.config.properties.QueueMessageProperties;
import com.hason.dtp.message.entity.Message;
import com.hason.dtp.message.entity.constant.MessageDataType;
import com.hason.dtp.message.service.MessageService;
import com.hason.dtp.test.message.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.UUID;

/**
 * 消息服务单元测试
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/7
 */
public class MessageServiceTest extends BaseTest {

    @Autowired
    private MessageService messageService;
    @Autowired
    private QueueMessageProperties properties;

    @Test
    public void testSaveAndWait() {
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDataType(MessageDataType.JSON);
        message.setMessageBody(JsonMapper.INSTANCE.toJson("测试内容"));
        message.setConsumerQueue(properties.getUserPointQueue());
        messageService.saveWaitingConfirm(message);
        System.out.println("### saveAndWaiting: " + message);
    }

    @Test
    @Rollback(false)
    public void testConfirmAndSendMessage() {
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDataType(MessageDataType.JSON);
        message.setMessageBody(JsonMapper.INSTANCE.toJson("测试内容"));
        message.setConsumerQueue(properties.getUserPointQueue());
        messageService.saveWaitingConfirm(message);
        messageService.confirmAndSend(message.getMessageId());
        System.out.println("### confirmAndSendMessage: " + message);
    }
}
