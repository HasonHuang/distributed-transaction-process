package com.hason.dtp.account.service;

import com.hason.dtp.account.config.properties.QueueMessageProperties;
import com.hason.dtp.account.dao.UserRepository;
import com.hason.dtp.account.entity.User;
import com.hason.dtp.account.payload.RegistPoint;
import com.hason.dtp.account.service.client.MessageServiceClient;
import com.hason.dtp.core.exception.ErrorCode;
import com.hason.dtp.core.exception.ServiceException;
import com.hason.dtp.core.utils.JsonMapper;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.message.entity.Message;
import com.hason.dtp.message.entity.constant.MessageDataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.hason.dtp.core.utils.CheckUtil.notFalse;
import static com.hason.dtp.core.utils.CheckUtil.notNull;

/**
 * 用户业务实现类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/9
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private QueueMessageProperties properties;

    @Autowired
    private MessageServiceClient messageServiceClient;

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User register(User user) {
        notNull(user, "arg.null", "用户对象");
        notNull(user.getUsername(), "arg.null", "用户名");

        User exists = userRepository.findByUsername(user.getUsername());
        notFalse(exists == null, "arg.illegal", "用户名");

        user.setPoint(0);
        user.setBalance(BigDecimal.ZERO);
        user.setCreateTime(LocalDateTime.now());
        user.setModifiedTime(user.getCreateTime());
        user = save(user);

        // 因为发送可靠消息时需要用户ID，未注册无法获得用户ID，所以直接保存并发送
        Result<Message> messageResult = messageServiceClient.saveAndSendMessage(registerMessage(user));
        if (!messageResult.success()) {
            throw new ServiceException(messageResult.getError().getMessage(), ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return user;
    }

    /**
     * 生成注册赠送积分的消息
     *
     * @param user 用户
     * @return Message
     */
    private Message registerMessage(User user) {
        Message message = new Message();
        message.setField1(user.getId().toString());
        message.setConsumerQueue(properties.getUserPointQueue());
        message.setMessageDataType(MessageDataType.JSON);
        message.setMessageId(UUID.randomUUID().toString());

        RegistPoint point = new RegistPoint();
        point.setMessageId(message.getMessageId());
        point.setUserId(user.getId());
        message.setMessageBody(JsonMapper.INSTANCE.toJson(point));

        return message;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User save(User user) {
        notNull(user, "arg.null", "用户对象");
        notNull(user.getUsername(), "arg.null", "用户名");
        notNull(user.getPassword(), "arg.null", "用户密码");
        notNull(user.getBalance(), "arg.null", "用户余额");
        notNull(user.getPoint(), "arg.null", "用户积分");
        userRepository.save(user);
        return user;
    }

    @Override
    public User get(Long userId) {
        notNull(userId, "arg.null", "用户ID");
        return userRepository.findOne(userId);
    }

}
