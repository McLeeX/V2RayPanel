package me.mclee.v2ray.panel.entity.v2ray;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Method {
    aes_256_cfb("aes-256-cfb"),
    aes_128_cfb("aes_128_cfb"),
    chacha20("chacha20"),
    chacha20_ietf("chacha20-ietf"),
    aes_256_gcm("aes-256-gcm"),
    aes_128_gcm("aes-128-gcm"),
    chacha20_poly1305("chacha20-poly1305"),
    chacha20_ietf_poly1305("chacha20-ietf-poly1305");
    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    Method(String value) {
        this.value = value;
    }
}
