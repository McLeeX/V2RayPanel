package me.mclee.v2ray.panel.entity.v2ray.routing;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Network {
    tcp("tcp"), udp("udp"), tcp_udp("tcp,udp");

    private String value;

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    Network(String value) {
        this.value = value;
    }

}
