package com.hason.dtp.account.point.service;

/**
 * 积分业务接口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/15
 */
public interface PointService {

    /**
     * （幂等）为新注册用户增加积分
     *
     * @param userId 用户ID
     * @param add 增加的积分
     * @return User
     */
    void addRegistPoint(Long userId, int add);

}
