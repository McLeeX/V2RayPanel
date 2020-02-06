package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.freedom;

import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;

public class Freedom extends OutboundSettings {
    private DomainStrategy domainStrategy;
    private String redirect;
    private Integer userLevel;

    public DomainStrategy getDomainStrategy() {
        return domainStrategy;
    }

    public void setDomainStrategy(DomainStrategy domainStrategy) {
        this.domainStrategy = domainStrategy;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
}
