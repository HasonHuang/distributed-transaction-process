package com.hason.dtp.message.service;

import com.hason.dtp.core.exception.ServiceException;
import com.hason.dtp.message.config.properties.QueueMessageProperties;
import com.hason.dtp.message.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.hason.dtp.message.util.MessageUtils.canRetry;

/**
 * （消息恢复子系统）用户积分的消息恢复处理器
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/12
 */
@Service
public class AccountPointRecoveryHandlerService implements RecoveryHandlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountPointRecoveryHandlerService.class);

    @Autowired
    private QueueMessageProperties properties;
    @Autowired
    private MessageService messageService;

    @Override
    public boolean canHandle(Message message) {
        return properties.getUserPointQueue().equals(message.getConsumerQueue());
    }

    @Override
    public void handle(Message message) throws ServiceException {
        LOGGER.debug("开始处理消息[{}]", message.getMessageId());

        // 判断重发次数，如果超过最大限制，设置为死亡并退出
        // 现在是第几次重试
        int currentRetryCount = message.getMessageRetryCount() + 1;
        if (!canRetry(properties.getRetry().getMax(), currentRetryCount)) {
            messageService.setToDead(message.getMessageId());
            return;
        }

        // 判断是否达到了重发的时间间隔
        if (!canSend(message.getEditTime(), currentRetryCount)) {
            LOGGER.debug("消息未达到重发时间条件");
            return;
        }

        // 重发消息
        messageService.reSend(message);

        LOGGER.debug("结束处理消息[{}]", message.getMessageId());
    }

    /**
     * 是否可以发送消息
     *
     * @param previous 上一次发送的时间
     * @param currentRetry 现在是第几次重发
     * @return true 可以， false 不可以
     */
    private boolean canSend(LocalDateTime previous, Integer currentRetry) {
        LocalDateTime nextTime = calNextSendTime(previous, currentRetry);
        return !LocalDateTime.now().isBefore(nextTime);
    }

    /**
     * 计算出下一次发送的时间点
     *
     * @param previous 上一次发送的时间
     * @param currentRetry 现在是第几次重发
     * @return 下一次发送的时间
     */
    private LocalDateTime calNextSendTime(LocalDateTime previous, Integer currentRetry) {
        Integer duration = properties.getRetry().getDuration().get(currentRetry);
        return previous.plusMinutes(duration);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
