package com.hason.dtp.tcc.account.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户控制器
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/9
 */
@Controller
public class UserPageController {

    /**
     * 注册页面
     *
     * @return
     */
    @GetMapping(value = "/register")
    public String registerPage() {
        return "register";
    }

}
