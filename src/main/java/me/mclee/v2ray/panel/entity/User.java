package me.mclee.v2ray.panel.entity;

/**
 * Table: USER
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}