package com.hason.dtp.tcc.account.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/4
 */
@Table(schema = "public", name = "user")
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 7020289766887330973L;

    @Id
    /**
     * name: 唯一的序列号生成器的名字，用于被一个或多个类引用
     * sequenceName: 数据库的序列名字
     * allocationSize: 序列号的增量
     */
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    private String username;

    private String password;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    // 默认驼峰风格转换成下划线风格，故无需声明数据库字段名
//    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("password", password)
                .append("createTime", createTime)
                .append("modifiedTime", modifiedTime)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
