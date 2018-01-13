package com.hason.dtp.tcc.capital.dto;

import java.math.BigDecimal;

/**
 * 创建订单的参数
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/1/13
 */
public class CreateOrderDto<T> {

    /** 充值的金额 */
    private BigDecimal amont;

    /** 附带数据 */
    private T data;

    public BigDecimal getAmont() {
        return amont;
    }

    public void setAmont(BigDecimal amont) {
        this.amont = amont;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
