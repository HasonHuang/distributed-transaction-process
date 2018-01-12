package com.hason.dtp.message.service;

/**
 * （消息状态确认子系统）消息状态服务接口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/8
 */
public interface MessageStatusService {

    /**
     * 处理状态为“待确认”且已超时的消息
     */
    void handleWaitConfirmTimeout();

    /**
     * 处理状态为“发送中”且已超时的消息
     */
    void handleSendingTimeout();

}
