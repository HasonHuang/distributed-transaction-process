package com.hason.dtp.message.service;

import com.hason.dtp.core.exception.ServiceException;
import com.hason.dtp.message.entity.Message;
import org.springframework.core.Ordered;

/**
 * （消息恢复子系统）消息恢复处理器
 *
 * <p>
 *     作用：针对状态为发送中，但超时未被消费的消息，对其进行恢复并发送
 * </p>
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/12
 */
public interface RecoveryHandlerService extends Ordered {

    /**
     * 判断是否可以处理消息
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
