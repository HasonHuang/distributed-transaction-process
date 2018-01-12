package com.hason.dtp.message.entity;


import com.hason.dtp.message.entity.constant.MessageDataType;
import com.hason.dtp.message.entity.constant.MessageStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 事务消息
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/19
 */
@Entity
@Table(schema = "public", name = "message")
public class Message {

    @Id
    private String id;

    private String messageId;

    @Enumerated(EnumType.STRING)
    private MessageDataType messageDataType;

    private String messageBody;

    private Integer messageRetryCount = 0;

    private String consumerQueue;

    private Boolean isDead;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    private Integer version = 0;

    private String creator;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime editTime;

    private String field1;

    private String field2;

    private String field3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public MessageDataType getMessageDataType() {
        return messageDataType;
    }

    public void setMessageDataType(MessageDataType messageDataType) {
        this.messageDataType = messageDataType;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Integer getMessageRetryCount() {
        return messageRetryCount;
    }

    public void setMessageRetryCount(Integer messageRetryCount) {
        this.messageRetryCount = messageRetryCount;
    }

    public String getConsumerQueue() {
        return consumerQueue;
    }

    public void setConsumerQueue(String consumerQueue) {
        this.consumerQueue = consumerQueue;
    }

    public Boolean getDead() {
        return isDead;
    }

    public void setDead(Boolean dead) {
        isDead = dead;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}
