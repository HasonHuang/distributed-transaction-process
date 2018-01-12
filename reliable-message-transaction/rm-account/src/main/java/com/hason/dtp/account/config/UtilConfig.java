package com.hason.dtp.account.config;

import com.hason.dtp.core.utils.CheckUtil;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化工具
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/6
 */
@Configuration
public class UtilConfig {

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(MessageSource source) {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setTargetClass(CheckUtil.class);
        bean.setTargetMethod("setSource");
        bean.setArguments(new Object[]{ source });
        return bean;
    }

}
