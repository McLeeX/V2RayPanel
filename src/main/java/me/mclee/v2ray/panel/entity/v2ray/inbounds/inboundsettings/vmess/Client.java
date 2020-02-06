package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.vmess;

import java.util.UUID;

public class Client {
    private UUID id;
    private Integer level;
    private Integer alterId;
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAlterId() {
        return alterId;
    }

    public void setAlterId(Integer alterId) {
        this.alterId = alterId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
