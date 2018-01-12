package com.hason.dtp.tcc.capital.controller;

import com.hason.dtp.core.support.tcc.TransactionEntity;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.core.utils.result.ResultBuilder;
import com.hason.dtp.tcc.account.entity.User;
import com.hason.dtp.tcc.capital.api.CapitalApi;
import com.hason.dtp.tcc.capital.entity.CapitalAccount;
import com.hason.dtp.tcc.capital.service.CapitalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * 资金账户 Controller
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/12
 */
@RestController
public class CapitalController implements CapitalApi {

    @Autowired
    private CapitalAccountService capitalAccountService;

    @PostMapping(value = "/capitals/accounts",
            consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @Override
    public Result<CapitalAccount> create(@RequestBody TransactionEntity<User> entity) {
        return ResultBuilder.newInstance(
                capitalAccountService.create(entity.getContext(), entity.getBody()));
    }

}
