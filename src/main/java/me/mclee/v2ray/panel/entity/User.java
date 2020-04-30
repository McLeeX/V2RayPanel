package me.mclee.v2ray.panel.entity;

import lombok.Data;

/**
 * Table: USER
 */
@Data
public class User {
    /**
     * Column: ID
     * Remark: 用户id
     */
    private Integer id;

    /**
     * Column: NAME
     * Remark: 用户名
     */
    private String name;

    /**
     * Column: EMAIL
     * Remark: 用户邮箱
     */
    private String email;

    /**
     * Column: PASSWORD
     * Remark: 密码
     */
    private String password;

    /**
     * Column: ROLE
     * Remark: 角色
     */
    private Integer role;
}