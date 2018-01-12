package com.hason.dtp.account.point.api;

import com.hason.dtp.core.support.MediaTypes;
import com.hason.dtp.core.utils.result.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 账户积分 API
 *
 * (为了简单示范，积分操作作为独立项目，测试时会停掉各种项目)
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/15
 */
public interface PointApi {

    /**
     *  注册成功后，赠送积分
     *
     * @param userId 用户ID
     * @return {@code Result<?>}
     */
    @RequestMapping(value = "/users/{userId}/points",
            method = RequestMethod.POST, consumes = MediaTypes.JSON)
    Result<?> addRegistPoint(@PathVariable("userId") Long userId);

}
