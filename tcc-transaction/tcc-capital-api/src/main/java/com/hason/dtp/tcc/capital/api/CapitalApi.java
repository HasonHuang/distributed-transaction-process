package com.hason.dtp.tcc.capital.api;

import com.hason.dtp.core.support.tcc.TransactionEntity;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.capital.entity.CapitalAccount;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 资金账户 API
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/12
 */
public interface CapitalApi {

    /**
     * 为用户创建资金账户
     *
     * @param entity 事务实体
     * @return {@code Result<CapitalAccount>}
     */
    @RequestMapping(value = "/capitals/accounts", method = POST,
            consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    Result<CapitalAccount> create(@RequestBody TransactionEntity<User> entity);

}
