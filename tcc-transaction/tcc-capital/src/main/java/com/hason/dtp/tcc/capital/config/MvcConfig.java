package com.hason.dtp.tcc.capital.config;

import com.hason.dtp.core.exception.RestExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * MVC 配置类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/23
 */
@Configuration
@ComponentScan(basePackageClasses = RestExceptionHandler.class)
public class MvcConfig extends WebMvcConfigurationSupport {

}
