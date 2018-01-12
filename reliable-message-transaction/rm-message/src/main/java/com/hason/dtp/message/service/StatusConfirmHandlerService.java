package com.hason.dtp.message.service;

import com.hason.dtp.core.exception.ServiceException;
import com.hason.dtp.message.entity.Message;
import org.springframework.core.Ordered;

/**
 * （消息状态确认子系统）消息状态确认处理器
 *
 * <br>
 *
 * 由于每个消息队列里的消息业务不一致，各个消息队列都应实现一个自己的状态处理器
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/9
 */
public interface StatusConfirmHandlerService extends Ordered {

    /**
     * 能否处理该消息
     *
     * @param message 消息
     * @return true 可以处理， false 不能处理
     */
    boolean canHandle(Message message);

    /**
     * 处理消息
     *
     * @param message 消息
     */
    void handle(Message message) throws ServiceException;

}
