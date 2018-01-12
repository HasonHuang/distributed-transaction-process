package com.hason.dtp.account.config;

import com.hason.dtp.core.exception.RestExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * MVC 配置类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/23
 */
@Configuration
@ComponentScan(basePackageClasses = RestExceptionHandler.class)
public class MvcConfig {
}
