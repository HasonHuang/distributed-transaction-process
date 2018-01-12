package com.hason.dtp.account.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/18
 */
@Table(schema = "public", name = "user")
@Entity
public class User {

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    private String username;

    private String password;

    private BigDecimal balance;

    private Integer point;

    @Column(name = "create_time")
    private LocalDateTime createTime;

//    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("password", password)
                .append("balance", balance)
                .append("point", point)
                .append("createTime", createTime)
                .append("modifiedTime", modifiedTime)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public User setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public Integer getPoint() {
        return point;
    }

    public User setPoint(Integer point) {
        this.point = point;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public User setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public User setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
        return this;
    }
}
