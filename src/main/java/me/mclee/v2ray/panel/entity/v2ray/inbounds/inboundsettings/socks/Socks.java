package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.socks;

import me.mclee.v2ray.panel.entity.v2ray.Account;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;

import java.util.List;

public class Socks extends InboundSettings {
    private Auth auth;
    List<Account> accounts;
    private Boolean udp;
    private String ip;
    private Integer userLevel;

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Boolean getUdp() {
        return udp;
    }

    public void setUdp(Boolean udp) {
        this.udp = udp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
}
