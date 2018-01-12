package com.hason.dtp.account.service;

import com.hason.dtp.account.entity.User;

/**
 * 用户业务接口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/9
 */
public interface UserService {

    /**
     * 注册用户
     *
     * @param user 用户
     * @return User
     */
    User register(User user);

    /**
     * 保存用户
     *
     * @param user 用户
     * @return User
     */
    User save(User user);

    /**
     * 获取用户
     *
     * @param userId 用户id
     * @return User
     */
    User get(Long userId);

}
