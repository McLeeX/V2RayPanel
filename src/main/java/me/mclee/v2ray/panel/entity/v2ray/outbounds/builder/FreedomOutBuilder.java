package me.mclee.v2ray.panel.entity.v2ray.outbounds.builder;

import lombok.Getter;
import me.mclee.v2ray.panel.entity.v2ray.Protocol;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.Outbound;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.freedom.DomainStrategy;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.freedom.Freedom;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.proxysettings.ProxySettings;

@Getter
public class FreedomOutBuilder {
    private String tag;
    private DomainStrategy domainStrategy;
    private String redirect;
    private Integer userLevel;
    private String proxySettingTag;

    public FreedomOutBuilder(String tag) {
        this.tag = tag;
    }

    public Outbound build() {
        Outbound outbound = new Outbound();
        outbound.setProtocol(Protocol.Freedom);
        outbound.setTag(tag);
        Freedom freedomSettings = new Freedom();
        outbound.setSettings(freedomSettings);
        freedomSettings.setDomainStrategy(domainStrategy);
        freedomSettings.setRedirect(redirect);
        freedomSettings.setUserLevel(userLevel);
        ProxySettings proxySettings = new ProxySettings();
        proxySettings.setTag(proxySettingTag);
        outbound.setProxySettings(proxySettings);
        return outbound;
    }

    public FreedomOutBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public FreedomOutBuilder setDomainStrategy(DomainStrategy domainStrategy) {
        this.domainStrategy = domainStrategy;
        return this;
    }

    public FreedomOutBuilder setRedirect(String redirect) {
        this.redirect = redirect;
        return this;
    }

    public FreedomOutBuilder setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
        return this;
    }
}
