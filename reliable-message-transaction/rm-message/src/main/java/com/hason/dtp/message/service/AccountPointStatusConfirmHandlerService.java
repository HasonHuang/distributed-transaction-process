package com.hason.dtp.message.service;

import com.hason.dtp.account.entity.User;
import com.hason.dtp.core.exception.ErrorCode;
import com.hason.dtp.core.exception.ServiceException;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.message.config.properties.QueueMessageProperties;
import com.hason.dtp.message.entity.Message;
import com.hason.dtp.message.service.client.UserServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

/**
 * （消息状态确认子系统）账户积分的消息状态确认处理器
 *
 * <p>
 *     模块功能：用户注册后会发送一条积分消息，积分系统接收到消息后，为用户增加积分
 *     该处理器的功能： 积分消息会因为各种原因无法确认并发送消息，该处理器专门处理这些异常消息确保消息可达性
 * </p>
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/9
 */
@Service
public class AccountPointStatusConfirmHandlerService implements StatusConfirmHandlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountPointStatusConfirmHandlerService.class);

    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private MessageService messageService;

    @Autowired
    private QueueMessageProperties properties;

    @Override
    public boolean canHandle(Message message) {
        return properties.getUserPointQueue().equals(message.getConsumerQueue());
    }

    @Override
    public void handle(Message message) throws ServiceException {
        LOGGER.debug("开始处理消息[{}]", message.getMessageId());

        try {
            String userIdStr = message.getField1();
            Long userId = Long.valueOf(userIdStr);
            Result<User> userResult = userServiceClient.get(userId);
            User user = userResult.getData();

            if (user == null) {
                // 用户注册失败，删除积分消息
                LOGGER.debug("用户注册失败，删除消息[{}]", message.getMessageId());
                messageService.deleteByMessageId(message.getMessageId());
            } else {
                // 用户注册成功，确认并发送积分消息
                LOGGER.debug("确认并发送消息[{}]", message.getMessageId());
                messageService.confirmAndSend(message.getMessageId());
            }

        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug("结束处理消息[{}]", message.getMessageId());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
