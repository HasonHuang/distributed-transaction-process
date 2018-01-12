package com.hason.dtp.message.consumer.receiver;

import com.hason.dtp.account.payload.RegistPoint;
import com.hason.dtp.core.utils.JsonMapper;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.message.consumer.service.MessageServiceClient;
import com.hason.dtp.message.consumer.service.PointServiceClient;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户积分消息消费端
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/12
 */
@Component
@RabbitListener(queues = "${queue.message.userPointQueue}")
public class UserPointReceiver {

    @Autowired
    private MessageServiceClient messageServiceClient;

    @Autowired
    private PointServiceClient pointServiceClient;

    @RabbitHandler
    public void process(String msg) {
        // 这里转换成相应的对象还有问题，确保全部都是 json ?
        RegistPoint payload = JsonMapper.INSTANCE.fromJson(msg, RegistPoint.class);
        Result<?> result = pointServiceClient.addRegistPoint(payload.getUserId());
        if (result.success()) {
            // 操作成功后，删除可靠消息；失败则不处理，等待超时处理
            messageServiceClient.deleteMessageByMessageId(payload.getMessageId());
        }
    }

}
