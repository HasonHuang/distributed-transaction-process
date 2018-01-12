package com.hason.dtp.message.controller;

import com.hason.dtp.core.support.MediaTypes;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.core.utils.result.ResultBuilder;
import com.hason.dtp.message.api.MessageApi;
import com.hason.dtp.message.entity.Message;
import com.hason.dtp.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * 消息 Controller
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/23
 */
@RestController
public class MessageController implements MessageApi {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/messages", method = POST, consumes = MediaTypes.JSON)
    @Override
    public Result<Message> saveMessageWaitingConfirm(@RequestBody Message message) {
        return ResultBuilder.newInstance(messageService.saveWaitingConfirm(message));
    }

    @RequestMapping(value = "/messages/{messageId}/confirm-send", method = PATCH)
    @Override
    public Result<?> confirmAndSendMessage(@PathVariable("messageId") String messageId) {
        messageService.confirmAndSend(messageId);
        return ResultBuilder.newInstance();
    }

    @RequestMapping(value = "/messages/send", method = POST, consumes = MediaTypes.JSON)
    @Override
    public Result<Message> saveAndSendMessage(@RequestBody Message message) {
        return ResultBuilder.newInstance(messageService.saveAndSend(message));
    }

    @RequestMapping(value = "/messages/direct-send", method = POST, consumes = MediaTypes.JSON)
    @Override
    public Result<?> directSendMessage(@RequestBody Message message) {
        messageService.directSend(message);
        return ResultBuilder.newInstance();
    }

    @RequestMapping(value = "/messages", method = PUT, consumes = MediaTypes.JSON)
    @Override
    public Result<?> reSendMessage(@RequestBody Message message) {
        messageService.reSend(message);
        return ResultBuilder.newInstance();
    }

    @RequestMapping(value = "/messages/{messageId}/send", method = PUT)
    @Override
    public Result<?> reSendMessageByMessageId(@PathVariable("messageId") String messageId) {
        messageService.reSendByMessageId(messageId);
        return ResultBuilder.newInstance();
    }

    @RequestMapping(value = "/messages/{messageId}/status/dead", method = PUT)
    @Override
    public Result<?> setMessageToAreadlyDead(@PathVariable("messageId") String messageId) {
        messageService.setToDead(messageId);
        return ResultBuilder.newInstance();
    }

    @RequestMapping(value = "/messages/{messageId}", method = GET)
    @Override
    public Result<Message> getMessageByMessageId(@PathVariable("messageId") String messageId) {
        return ResultBuilder.newInstance(messageService.getByMessageId(messageId));
    }

    @RequestMapping(value = "/messages/{messageId}", method = DELETE)
    @Override
    public Result<?> deleteMessageByMessageId(@PathVariable("messageId") String messageId) {
        messageService.deleteByMessageId(messageId);
        return ResultBuilder.newInstance();
    }

    @RequestMapping(value = "/queues/names/{queueName}", method = PUT)
    @Override
    public Result<?> reSendAllDeadMessageByQueueName(
            @PathVariable("queueName") String queueName,
            @RequestParam("batchSize") int batchSize) {
        messageService.reSendAllDeadByQueueName(queueName, batchSize);
        return ResultBuilder.newInstance();
    }
}
