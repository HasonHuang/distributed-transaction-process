package com.hason.dtp.message.util;

/**
 * 消息的工具类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/12
 */
public class MessageUtils {

    /**
     * 消息能否重发
     *
     * @param max 最大重发次数
     * @param current 当前是第几次重发
     * @return true 可以， false 不可以
     */
    public static boolean canRetry(int max, int current) {
        return max >= current;
    }

}
