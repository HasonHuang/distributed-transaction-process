package com.hason.dtp.message.service;

import com.hason.dtp.core.exception.CheckException;
import com.hason.dtp.core.exception.ServiceException;
import com.hason.dtp.message.entity.Message;

/**
 * 消息接口
 *
 * @author Huanghs
 * @since 2.0 
 * @date 2017/11/7
 */
public interface MessageService {

    /**
     * 预存储消息.
     */
    Message saveWaitingConfirm(Message message) throws CheckException, ServiceException;

    /**
     * 确认并发送消息.
     */
    void confirmAndSend(String messageId) throws CheckException, ServiceException;

    /**
     * 存储并发送消息.
     */
    Message saveAndSend(Message message) throws CheckException, ServiceException;

    /**
     * 直接发送消息.
     */
    void directSend(Message message) throws CheckException, ServiceException;

    /**
     * 重发消息.
     */
    void reSend(Message message) throws CheckException, ServiceException;

    /**
     * 根据messageId重发某条消息.
     */
    void reSendByMessageId(String messageId) throws CheckException, ServiceException;

    /**
     * 将消息标记为死亡消息.
     */
    void setToDead(String messageId) throws CheckException, ServiceException;

    /**
     * 根据消息ID获取消息
     */
    Message getByMessageId(String messageId) throws CheckException, ServiceException;

    /**
     * 根据消息ID删除消息
     */
    void deleteByMessageId(String messageId) throws CheckException, ServiceException;

    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    void reSendAllDeadByQueueName(String queueName, int batchSize) throws CheckException, ServiceException;

    /**
     * 获取分页数据
     */
//    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

}
