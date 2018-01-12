package com.hason.dtp.tcc.integral.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * 用户积分
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/5
 */
@Table(schema = "public", name = "point")
@Entity
public class Point {

    @Id
    @SequenceGenerator(name = "point_seq", sequenceName = "point_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "point_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long userId;

    private Long value;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", userId)
                .append("value", value)
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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
