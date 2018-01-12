package com.hason.dtp.tcc.capital.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 资金账户
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/4
 */
@Table(schema = "public", name = "capital_account")
@Entity
public class CapitalAccount {

    @Id
    @SequenceGenerator(name = "capital_account_seq", sequenceName = "capital_account_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "capital_account_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private BigDecimal balanceAmount;

    private Long userId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("balanceAmount", balanceAmount)
                .append("userId", userId)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
