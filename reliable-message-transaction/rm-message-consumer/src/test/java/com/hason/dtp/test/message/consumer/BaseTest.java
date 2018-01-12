package com.hason.dtp.test.message.consumer;

import com.hason.dtp.message.consumer.RmMessageConsumerApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RmMessageConsumerApplication.class)
public abstract class BaseTest {
}
