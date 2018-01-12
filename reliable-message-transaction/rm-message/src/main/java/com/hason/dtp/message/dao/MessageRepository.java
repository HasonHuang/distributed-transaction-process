package com.hason.dtp.message.dao;

import com.hason.dtp.message.entity.Message;
import com.hason.dtp.message.entity.constant.MessageStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

/**
 * 事务消息 Repository
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/19
 */
public interface MessageRepository extends JpaRepository<Message, String>, JpaSpecificationExecutor<Message> {

    /**
     * 根据消息ID，获取记录
     *
     * @param messageId 消息ID
     * @return Message
     */
    Message findByMessageId(String messageId);

    /**
     * 根据消息队列和死亡状态，分页获取数据
     *
     * @param consumerQueue 消息队列
     * @param dead 是否死亡
     * @param page 分页参数
     * @return list
     */
    Page<Message> findByConsumerQueueAndDead(String consumerQueue, Boolean dead, Pageable page);

    /**
     * 根据状态、创建时间、是否死亡，分页查询记录
     *
     * @param status 状态
     * @param dead 是否死亡
     * @param createTimeBefore 创建时间在 createTimeBefore 之前
     * @param pageable 分页参数
     * @return Page
     */
    @Query(value = "SELECT msg FROM Message msg WHERE msg.status = :status AND msg.isDead = :dead AND msg.createTime < :createTimeBefore")
    Page<Message> findByStatusAndCreateTime(
            @Param("status") MessageStatus status,
            @Param("dead") Boolean dead,
            @Param("createTimeBefore") LocalDateTime createTimeBefore,
            Pageable pageable);

    /**
     * 根据messageId, 删除记录
     *
     * @param messageId 消息id
     */
    void deleteByMessageId(String messageId);
}
