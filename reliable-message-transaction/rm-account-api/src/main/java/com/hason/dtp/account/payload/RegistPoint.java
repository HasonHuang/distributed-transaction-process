package com.hason.dtp.account.payload;

/**
 * 新注册赠送积分的队列载体
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/12
 */
public class RegistPoint {

    /** 消息ID */
    private String messageId;
    /** 用户ID */
    private Long userId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
