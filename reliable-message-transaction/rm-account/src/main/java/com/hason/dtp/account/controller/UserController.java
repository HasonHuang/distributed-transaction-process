package com.hason.dtp.account.controller;

import com.hason.dtp.account.api.UserApi;
import com.hason.dtp.account.entity.User;
import com.hason.dtp.account.service.UserService;
import com.hason.dtp.core.support.MediaTypes;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.core.utils.result.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/9
 */
@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users/register", consumes = MediaTypes.JSON)
    @Override
    public Result<User> register(@RequestBody User user) {
        return ResultBuilder.newInstance(userService.register(user));
    }

    @PostMapping(value = "/users", consumes = MediaTypes.JSON)
    @Override
    public Result<User> save(@RequestBody User user) {
        return ResultBuilder.newInstance(userService.save(user));
    }

    @GetMapping("/users/{userId}")
    @Override
    public Result<User> get(@PathVariable Long userId) {
        return ResultBuilder.newInstance(userService.get(userId));
    }

}
