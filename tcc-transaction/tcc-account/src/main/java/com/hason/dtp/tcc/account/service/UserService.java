package com.hason.dtp.tcc.account.service;


import com.hason.dtp.tcc.account.entity.User;

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
     * （基于接口代理时，需要声明接口）确认注册
     *
     * @param user 用户
     * @return User
     */
    User confirmRegister(User user);

    /**
     * （基于接口代理时，需要声明接口）取消注册
     *
     * @param user 用户
     * @return User
     */
    User cancelRegister(User user);

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

    /**
     * 获取用户
     *
     * @param username 用户名
     * @return User
     */
    User get(String username);

}
