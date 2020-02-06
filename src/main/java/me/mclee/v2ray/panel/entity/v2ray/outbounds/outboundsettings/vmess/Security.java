package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.vmess;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Security {
    aes128Gcm("aes-128-gcm"), chacha20Poly1305("chacha20-poly1305"), auto("auto"), none("none");
    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    Security(String value) {
        this.value = value;
    }
}
