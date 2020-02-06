package me.mclee.v2ray.panel.entity.v2ray;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Protocol {
    Blackhole("blackhole"),
    DNS("dns"),
    DokodemoDoor("dokodemo-door"),
    Freedom("freedom"),
    HTTP("http"),
    MTProto("mtproto"),
    Shadowsocks("shadowsocks"),
    Socks("socks"),
    VMess("vmess");
    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    Protocol(String value) {
        this.value = value;
    }
}
