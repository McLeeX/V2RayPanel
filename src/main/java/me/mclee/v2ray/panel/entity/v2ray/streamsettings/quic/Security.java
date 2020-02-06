package me.mclee.v2ray.panel.entity.v2ray.streamsettings.quic;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Security {
    none("none"), aes128Gcm("aes-128-gcm"), chacha20Poly1305("chacha20-poly1305");
    private String value;

    public String getValue() {
        return value;
    }

    @JsonValue
    public void setValue(String value) {
        this.value = value;
    }

    Security(String value) {
        this.value = value;
    }
}
