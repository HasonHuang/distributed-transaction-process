package com.hason.dtp.tcc.integral.api;

import com.hason.dtp.core.support.tcc.TransactionEntity;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.integral.entity.Point;
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
     * @return
     */
    @RequestMapping(value = "/points", method = POST,
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    Result<Point> create(@RequestBody TransactionEntity<User> entity);

}
