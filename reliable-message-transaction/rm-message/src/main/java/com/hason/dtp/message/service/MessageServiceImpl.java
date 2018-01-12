package com.hason.dtp.message.service;

import com.hason.dtp.core.exception.CheckException;
import com.hason.dtp.core.exception.ServiceException;
import com.hason.dtp.message.config.properties.QueueMessageProperties;
import com.hason.dtp.message.dao.MessageRepository;
import com.hason.dtp.message.entity.Message;
import com.hason.dtp.message.entity.constant.MessageStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import static com.hason.dtp.core.utils.CheckUtil.notEmpty;
import static com.hason.dtp.core.utils.CheckUtil.notNull;
import static com.hason.dtp.message.util.MessageUtils.canRetry;

/**
 * 消息实现类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/7
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private QueueMessageProperties properties;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Message saveWaitingConfirm(Message message) throws CheckException, ServiceException {
        notNull(message, "arg.null", "消息对象");
        notEmpty(message.getConsumerQueue(), "arg.null", "消息队列");
        notEmpty(message.getMessageId(), "arg.null", "消息ID");

        LocalDateTime now = LocalDateTime.now();
        message.setId(id());
        message.setStatus(MessageStatus.TO_BE_CONFIRMED);
        message.setDead(Boolean.FALSE);
        message.setMessageRetryCount(0);
        message.setCreateTime(now);
        message.setEditTime(now);
        messageRepository.save(message);
        return message;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void confirmAndSend(String messageId) throws CheckException, ServiceException {
        notEmpty(messageId, "arg.null", "消息ID");

        Message message = getByMessageId(messageId);
        notNull(message, "arg.null", "根据消息ID获取的消息");

        // 发送中
        message.setStatus(MessageStatus.SENDING);
        message.setEditTime(LocalDateTime.now());
        messageRepository.save(message);

        // 发送消息
        directSendNotCheck(message);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Message saveAndSend(Message message) throws CheckException, ServiceException {
        notNull(message, "arg.null", "消息对象");
        notEmpty(message.getConsumerQueue(), "arg.null", "消息队列");

        LocalDateTime now = LocalDateTime.now();
        message.setId(id());
        message.setStatus(MessageStatus.SENDING);
        message.setDead(Boolean.FALSE);
        message.setMessageRetryCount(0);
        message.setCreateTime(now);
        message.setEditTime(now);
        messageRepository.save(message);

        // 发送消息
        directSendNotCheck(message);
        return message;
    }

    @Override
    public void directSend(Message message) throws CheckException, ServiceException {
        notNull(message, "arg.null", "消息对象");
        notEmpty(message.getConsumerQueue(), "arg.null", "消息队列");
        // 发送消息
        directSendNotCheck(message);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void reSend(Message message) throws CheckException, ServiceException {
        notNull(message, "arg.null", "消息对象");
        notEmpty(message.getConsumerQueue(), "arg.null", "消息队列");

        message.setDead(isDead(message));
        message.setMessageRetryCount(message.getMessageRetryCount() + 1);
        message.setEditTime(LocalDateTime.now());
        messageRepository.save(message);

        directSendNotCheck(message);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void reSendByMessageId(String messageId) throws CheckException, ServiceException {
        Message message = getByMessageId(messageId);
        notNull(message, "arg.null", "根据消息ID获取的消息");

        message.setDead(isDead(message));
        message.setMessageRetryCount(message.getMessageRetryCount() + 1);
        message.setEditTime(LocalDateTime.now());
        messageRepository.save(message);

        directSendNotCheck(message);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void setToDead(String messageId) throws CheckException, ServiceException {
        Message message = getByMessageId(messageId);
        notNull(message, "arg.null", "根据消息ID获取的消息");

        message.setDead(Boolean.TRUE);
        message.setEditTime(LocalDateTime.now());
        messageRepository.save(message);
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Override
    public Message getByMessageId(String messageId) throws CheckException, ServiceException {
        return messageRepository.findByMessageId(messageId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByMessageId(String messageId) throws CheckException, ServiceException {
        messageRepository.deleteByMessageId(messageId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void reSendAllDeadByQueueName(String queueName, int batchSize)
            throws CheckException, ServiceException {

        int pageNum = 0;
        int totalPage = 1;
        while (pageNum++ < totalPage) {
            Sort.Order order = new Sort.Order("createTime");
            Sort sort = new Sort(order);
            Pageable pageable = new PageRequest(pageNum, batchSize, sort);
            Page<Message> page = messageRepository.findByConsumerQueueAndDead(queueName, Boolean.TRUE, pageable);

            totalPage = page.getTotalPages();
            reSendNoDead(page.getContent());
        }
    }

    /**
     * 重发消息并更新数据库记录，不设置死亡状态
     *
     * @param messages 消息列表
     */
    private void reSendNoDead(Collection<Message> messages) {
        for (Message message : messages) {
            reSendNoDead(message);
        }
    }

    /**
     * 重发消息并更新数据库记录，不设置死亡状态
     *
     * @param message 消息
     */
    private void reSendNoDead(Message message) {
        message.setEditTime(LocalDateTime.now());
        message.setMessageRetryCount(message.getMessageRetryCount() + 1);
        messageRepository.save(message);
        directSendNotCheck(message);
    }

    /**
     * 直接发送消息，不检查参数合法性
     *
     * @param message 消息
     */
    private void directSendNotCheck(Message message) {
        amqpTemplate.convertAndSend(message.getConsumerQueue(), message.getMessageBody());
    }

    /**
     * 判断消息是否满足死亡条件
     *
     * @param message 消息
     * @return boolean
     */
    private boolean isDead(Message message) {
        return !canRetry(properties.getRetry().getMax(), message.getMessageRetryCount() + 1);
    }

    private String id() {
        return UUID.randomUUID().toString();
    }

}
