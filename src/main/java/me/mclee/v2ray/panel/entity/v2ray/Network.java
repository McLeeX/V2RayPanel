package me.mclee.v2ray.panel.entity.v2ray;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashSet;
import java.util.Set;

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

    public Set<com.v2ray.core.common.net.Network> toGRpcSet() {
        Set<com.v2ray.core.common.net.Network> list = new HashSet<>();
        switch (this) {
            case tcp:
                list.add(com.v2ray.core.common.net.Network.TCP);
                break;
            case udp:
                list.add(com.v2ray.core.common.net.Network.UDP);
                break;
            case tcp_udp:
                list.add(com.v2ray.core.common.net.Network.TCP);
                list.add(com.v2ray.core.common.net.Network.UDP);
                break;
            default:
                break;
        }
        return list;
    }
}
