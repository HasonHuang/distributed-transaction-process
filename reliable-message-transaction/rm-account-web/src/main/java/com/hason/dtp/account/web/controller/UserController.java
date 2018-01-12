package com.hason.dtp.account.web.controller;

import com.hason.dtp.account.entity.User;
import com.hason.dtp.account.web.service.client.UserServiceClient;
import com.hason.dtp.core.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * 用户控制器
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/9
 */
@Controller
public class UserController {

    @Autowired
    private UserServiceClient userServiceClient;

    /**
     * 注册页面
     *
     * @return
     */
    @GetMapping(value = "/register")
    public String registerPage() {
        return "register";
    }

    /**
     * 提交注册
     *
     * @param user 用户
     * @return
     */
    @PostMapping(value = "/register")
    @ResponseBody
    public Result<User> register(User user) {
        return userServiceClient.register(user);
    }

}
