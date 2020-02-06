package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.shadowsocks;

import me.mclee.v2ray.panel.entity.v2ray.Method;
import me.mclee.v2ray.panel.entity.v2ray.Network;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;

public class Shadowsocks extends InboundSettings {
    private String email;
    private Method method;
    private String password;
    private Integer level;
    private Boolean ota;
    private Network network;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
}
