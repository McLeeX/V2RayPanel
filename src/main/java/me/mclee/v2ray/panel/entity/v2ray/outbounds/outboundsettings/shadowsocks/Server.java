package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.shadowsocks;

import me.mclee.v2ray.panel.entity.v2ray.Method;

public class Server {
    private String email;
    private String address;
    private Integer port;
    private Method method;
    private String password;
    private Integer level;
    private Boolean ota;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getOta() {
        return ota;
    }

    public void setOta(Boolean ota) {
        this.ota = ota;
    }
}
