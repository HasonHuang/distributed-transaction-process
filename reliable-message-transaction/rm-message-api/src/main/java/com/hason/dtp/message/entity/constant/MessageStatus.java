package com.hason.dtp.message.entity.constant;

/**
 * 事务消息的状态
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/19
 */
public enum  MessageStatus {
    /** 待确认 */
    TO_BE_CONFIRMED,
    /** 待发送 */
    TO_BE_SEND,
    /** 发送中 */
    SENDING,
    /** 已发送 */
    SENDED,
}
