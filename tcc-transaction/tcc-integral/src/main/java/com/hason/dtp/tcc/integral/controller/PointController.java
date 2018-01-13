package com.hason.dtp.tcc.integral.controller;

import com.hason.dtp.core.support.tcc.TransactionEntity;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.core.utils.result.ResultBuilder;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.integral.api.PointApi;
import com.hason.dtp.tcc.integral.entity.Point;
import com.hason.dtp.tcc.integral.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * 积分 Controller
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/8
 */
@RestController
public class PointController implements PointApi {

    @Autowired
    private PointService pointService;

    @PostMapping(value = "/points", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Result<Point> create(@RequestBody TransactionEntity<User> entity) {
        return ResultBuilder.newInstance(pointService.create(entity.getContext(), entity.getBody()));
    }

    @PostMapping(value = "//users/{userId}/points",
            consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Result<?> incr(@PathVariable("userId") Long userId, @RequestBody TransactionEntity<Long> entity) {
        pointService.incr(entity.getContext(), userId, entity.getBody());
        return ResultBuilder.newInstance();
    }
}
