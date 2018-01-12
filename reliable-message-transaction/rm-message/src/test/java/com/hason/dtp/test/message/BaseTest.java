package com.hason.dtp.test.message;

import com.hason.dtp.message.RmMessageApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RmMessageApplication.class)
public abstract class BaseTest {
}
