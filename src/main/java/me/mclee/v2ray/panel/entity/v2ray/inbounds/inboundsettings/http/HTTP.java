package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.http;

import me.mclee.v2ray.panel.entity.v2ray.Account;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;

import java.util.List;

public class HTTP extends InboundSettings {
    private Integer timeout;
    private List<Account> accounts;
    private Boolean allowTransparent;
    private Integer userLevel;

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Boolean getAllowTransparent() {
        return allowTransparent;
    }

    public void setAllowTransparent(Boolean allowTransparent) {
        this.allowTransparent = allowTransparent;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
}
