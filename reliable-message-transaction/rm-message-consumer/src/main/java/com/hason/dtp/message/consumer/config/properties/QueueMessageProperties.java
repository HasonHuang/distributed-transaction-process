package com.hason.dtp.message.consumer.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 消息队列的属性配置
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/7
 */
@Configuration
@ConfigurationProperties(prefix = "queue.message")
@PropertySource(value = "classpath:application.yml")
public class QueueMessageProperties {

    private String userPointQueue;
    private Integer expire;

    /** 重试参数 */
    private Retry retry;

    public static class Retry {

        /** 最大重试次数 */
        private Integer max;

        /** 重发时间间隔 */
        private Map<Integer, Integer> duration;

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }

        public Map<Integer, Integer> getDuration() {
            return duration;
        }

        public void setDuration(Map<Integer, Integer> duration) {
            this.duration = duration;
        }
    }

    public String getUserPointQueue() {
        return userPointQueue;
    }

    public void setUserPointQueue(String userPointQueue) {
        this.userPointQueue = userPointQueue;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public Retry getRetry() {
        return retry;
    }

    public void setRetry(Retry retry) {
        this.retry = retry;
    }
}
