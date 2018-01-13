package com.hason.dtp.tcc.integral.api;

import com.hason.dtp.core.support.tcc.TransactionEntity;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.integral.entity.Point;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 积分系统 API
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/7
 */
public interface PointApi {

    /**
     * 创建用户的积分账户
     *
     * @param entity 事务实体
     * @return {@code Result<Point>}
     */
    @RequestMapping(value = "/points", method = POST,
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    Result<Point> create(@RequestBody TransactionEntity<User> entity);

    /**
     * 增加用户积分
     *
     * @param userId 用户ID
     * @param entity 事务实体
     * @return {@code Result<?>}，
     * 由于是异步确认，try 操作执行后返回的 Point 仍是旧值，所以就不返回了
     */
    @RequestMapping(value = "/users/{userId}/points", method = POST,
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    Result<?> incr(@PathVariable("userId") Long userId, @RequestBody TransactionEntity<Long> entity);

}
