package com.hason.dtp.tcc.capital.entity;

import com.hason.dtp.tcc.capital.entity.constant.CapitalOrderStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 资金账户订单
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/4
 */
@Table(schema = "public", name = "capital_order")
@Entity
public class CapitalOrder {

    @Id
    @SequenceGenerator(name = "capital_order_seq", sequenceName = "capital_order_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "capital_order_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long userId;

    private String merchantOrderNo;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private CapitalOrderStatus status;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", userId)
                .append("merchantOrderNo", merchantOrderNo)
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

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CapitalOrderStatus getStatus() {
        return status;
    }

    public void setStatus(CapitalOrderStatus status) {
        this.status = status;
    }
}
