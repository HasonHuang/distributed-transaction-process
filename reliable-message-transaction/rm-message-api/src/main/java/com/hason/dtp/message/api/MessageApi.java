package com.hason.dtp.message.api;

import com.hason.dtp.core.support.MediaTypes;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.message.entity.Message;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 可靠消息接口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/20
 */
public interface MessageApi {

    /**
     * 预存储消息.
     */
    @RequestMapping(value = "/messages",
            method = RequestMethod.POST, consumes = MediaTypes.JSON)
    Result<Message> saveMessageWaitingConfirm(@RequestBody Message message);

    /**
     * 确认并发送消息.
     */
    @RequestMapping(value = "/messages/{messageId}/confirm-send", method = PATCH)
    Result<?> confirmAndSendMessage(@PathVariable("messageId") String messageId);

    /**
     * 存储并发送消息.
     */
    @RequestMapping(value = "/messages/send",
            method = RequestMethod.POST, consumes = MediaTypes.JSON)
    Result<Message> saveAndSendMessage(@RequestBody Message message);

    /**
     * 直接发送消息.
     */
    @RequestMapping(value = "/messages/direct-send",
            method = RequestMethod.POST, consumes = MediaTypes.JSON)
    Result<?> directSendMessage(@RequestBody Message message);

    /**
     * 重发消息.
     */
    @RequestMapping(value = "/messages", method = PUT, consumes = MediaTypes.JSON)
    Result<?> reSendMessage(@RequestBody Message message);

    /**
     * 根据messageId重发某条消息.
     */
    @RequestMapping(value = "/messages/{messageId}/send", method = PUT)
    Result<?> reSendMessageByMessageId(@PathVariable("messageId") String messageId);

    /**
     * 将消息标记为死亡消息.
     */
    @RequestMapping(value = "/messages/{messageId}/status/dead", method = PUT)
    Result<?> setMessageToAreadlyDead(@PathVariable("messageId") String messageId);

    /**
     * 根据消息ID获取消息
     */
    @RequestMapping(value = "/messages/{messageId}", method = GET)
    Result<Message> getMessageByMessageId(@PathVariable("messageId") String messageId);

    /**
     * 根据消息ID删除消息
     */
    @RequestMapping(value = "/messages/{messageId}", method = DELETE)
    Result<?> deleteMessageByMessageId(@PathVariable("messageId") String messageId);

    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    @RequestMapping(value = "/queues/names/{queueName}", method = PUT)
    Result<?> reSendAllDeadMessageByQueueName(
            @PathVariable("queueName") String queueName,
            @RequestParam("batchSize") int batchSize);

    /**
     * 获取分页数据
     */
//    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

}
