package com.hason.dtp.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Hello, 测试
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/17
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Object hello() {
        return "Hi, " + LocalDateTime.now();
    }

}
