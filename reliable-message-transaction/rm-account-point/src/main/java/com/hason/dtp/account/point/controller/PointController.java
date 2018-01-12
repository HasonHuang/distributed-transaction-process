package com.hason.dtp.account.point.controller;

import com.hason.dtp.account.point.api.PointApi;
import com.hason.dtp.account.point.service.PointService;
import com.hason.dtp.core.support.MediaTypes;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.core.utils.result.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 积分 Controller
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/15
 */
@RestController
public class PointController implements PointApi {

    @Autowired
    private PointService pointService;

    @PostMapping(value = "/users/{userId}/points", consumes = MediaTypes.JSON)
    @Override
    public Result<?> addRegistPoint(@PathVariable Long userId) {
        pointService.addRegistPoint(userId, 10);
        return ResultBuilder.newInstance();
    }

}
