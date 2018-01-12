package com.hason.dtp.message.timer;

import com.hason.dtp.message.service.MessageStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 消息状态定时器，定时启动状态扫描任务
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/16
 */
@Component
public class MessageStatusTimer {

    @Autowired
    private MessageStatusService messageStatusService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void waitConfirmTimeout() {
        messageStatusService.handleWaitConfirmTimeout();
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void sendingTimeout() {
        messageStatusService.handleSendingTimeout();
    }

}
