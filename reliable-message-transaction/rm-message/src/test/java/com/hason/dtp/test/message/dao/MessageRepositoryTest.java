package com.hason.dtp.test.message.dao;

import com.hason.dtp.message.config.properties.QueueMessageProperties;
import com.hason.dtp.message.dao.MessageRepository;
import com.hason.dtp.message.entity.Message;
import com.hason.dtp.message.entity.constant.MessageDataType;
import com.hason.dtp.message.entity.constant.MessageStatus;
import com.hason.dtp.test.message.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * MessageRepository 单元测试
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/19
 */
public class MessageRepositoryTest extends BaseTest {

    @Autowired
    private QueueMessageProperties properties;
    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void testSave() {
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDataType(MessageDataType.JSON);
        message.setMessageBody("消息内容");
        message.setConsumerQueue(properties.getUserPointQueue());
        message.setDead(Boolean.FALSE);
        message.setStatus(MessageStatus.SENDED);
        message.setCreateTime(LocalDateTime.now());
        message.setEditTime(LocalDateTime.now());
        message.setField1(UUID.randomUUID().toString());

        messageRepository.save(message);


        System.out.println(message);
    }

    @Test
    @Rollback(false)
    public void testQueryPage() {
        testSave();
        Pageable pageable = new PageRequest(1, 10);
        Page<Message> page = messageRepository.findByStatusAndCreateTime(
                MessageStatus.SENDED, Boolean.FALSE, LocalDateTime.now(), pageable);
        Assert.notNull(page);
        System.out.println(page.getTotalElements());
    }

}
