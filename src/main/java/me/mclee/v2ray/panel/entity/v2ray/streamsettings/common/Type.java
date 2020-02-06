package me.mclee.v2ray.panel.entity.v2ray.streamsettings.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {
    none("none"),
    http("http"),
    srtp("srtp"),
    utp("utp"),
    wechatVideo("wechat-video"),
    dtls("dtls"),
    wireguard("wireguard");
    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    Type(String value) {
        this.value = value;
    }
}
