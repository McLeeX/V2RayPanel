package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.vmess;

import java.util.UUID;

public class User {
    private UUID id;
    private Integer alterId;
    private Security security;
    private Integer level;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getAlterId() {
        return alterId;
    }

    public void setAlterId(Integer alterId) {
        this.alterId = alterId;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
