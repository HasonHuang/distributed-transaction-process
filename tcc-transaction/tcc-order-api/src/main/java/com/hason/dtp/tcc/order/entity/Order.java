package com.hason.dtp.tcc.order.entity;

import com.hason.dtp.tcc.order.entity.constant.OrderStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 订单
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/5
 */
@Table(schema = "public", name = "order")
@Entity
public class Order {

    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "order_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "order_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long userId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", userId)
                .append("amount", amount)
                .append("status", status)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
