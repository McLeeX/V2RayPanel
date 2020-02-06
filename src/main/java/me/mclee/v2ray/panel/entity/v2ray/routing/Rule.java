package me.mclee.v2ray.panel.entity.v2ray.routing;

import me.mclee.v2ray.panel.entity.v2ray.Network;

import java.util.List;

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
    private String outBoundTag;
    private String balancerTag;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getDomain() {
        return domain;
    }

    public void setDomain(List<String> domain) {
        this.domain = domain;
    }

    public List<String> getIp() {
        return ip;
    }

    public void setIp(List<String> ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public List<String> getUser() {
        return user;
    }

    public void setUser(List<String> user) {
        this.user = user;
    }

    public List<String> getInboundTag() {
        return inboundTag;
    }

    public void setInboundTag(List<String> inboundTag) {
        this.inboundTag = inboundTag;
    }

    public List<Protocol> getProtocol() {
        return protocol;
    }

    public void setProtocol(List<Protocol> protocol) {
        this.protocol = protocol;
    }

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }

    public String getOutBoundTag() {
        return outBoundTag;
    }

    public void setOutBoundTag(String outBoundTag) {
        this.outBoundTag = outBoundTag;
    }

    public String getBalancerTag() {
        return balancerTag;
    }

    public void setBalancerTag(String balancerTag) {
        this.balancerTag = balancerTag;
    }
}
