package com.hason.dtp.test.account.dao;

import com.hason.dtp.account.dao.UserRepository;
import com.hason.dtp.account.entity.User;
import com.hason.dtp.test.account.BaseTest;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户dao单元测试
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/18
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void init() {
        user = new User();
        user.setCreateTime(LocalDateTime.now());
        user.setModifiedTime(LocalDateTime.now());
        user.setBalance(BigDecimal.TEN);
        user.setPoint(2);
        user.setUsername("Hason");
        user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
    }

    @Test
    public void test1Save() {
        userRepository.save(user);
    }

}
