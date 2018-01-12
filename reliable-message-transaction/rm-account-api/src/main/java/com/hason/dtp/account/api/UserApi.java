package com.hason.dtp.account.api;

import com.hason.dtp.account.entity.User;
import com.hason.dtp.core.support.MediaTypes;
import com.hason.dtp.core.utils.result.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户微服务接口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/9
 */
public interface UserApi {

    /**
     * 注册用户
     *
     * @param user 用户
     * @return {@code Result<User>}
     */
    @RequestMapping(value = "/users/register",
            method = RequestMethod.POST, consumes = MediaTypes.JSON)
    Result<User> register(@RequestBody User user);

    /**
     * 保存用户
     *
     * @param user 用户
     * @return {@code Result<User>}
     */
    @RequestMapping(value = "/users",
            method = RequestMethod.POST, consumes = MediaTypes.JSON)
    Result<User> save(@RequestBody User user);

    /**
     * 获取用户
     *
     * @param userId 用户id
     * @return {@code Result<User>}
     */
    @RequestMapping(value = "/users/{userId}")
    Result<User> get(@PathVariable("userId") Long userId);

}
