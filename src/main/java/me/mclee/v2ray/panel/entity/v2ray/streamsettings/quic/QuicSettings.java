package me.mclee.v2ray.panel.entity.v2ray.streamsettings.quic;


import me.mclee.v2ray.panel.entity.v2ray.streamsettings.common.Header;

public class QuicSettings {
    private Security security;
    private String key;
    private Header header;

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
