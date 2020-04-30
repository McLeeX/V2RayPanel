package me.mclee.v2ray.panel.entity.v2ray.routing;

import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.entity.v2ray.Network;

import java.util.List;

@Getter
@Setter
public class Rule {
    private String type = "field";
    private List<String> domain;
    private List<String> ip;
    private String port;
    private Network network;
    private List<String> source;
    private List<String> user;
    private List<String> inboundTag;
    private List<Protocol> protocol;
    private String attrs;
    private String outboundTag;
    private String balancerTag;
}
