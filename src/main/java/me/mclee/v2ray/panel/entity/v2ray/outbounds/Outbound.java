package me.mclee.v2ray.panel.entity.v2ray.outbounds;

import me.mclee.v2ray.panel.entity.v2ray.Protocol;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.mux.Mux;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.proxysettings.ProxySettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.StreamSettings;

public class Outbound {
    private String sendThrough;
    private Protocol protocol;
    private OutboundSettings settings;
    private StreamSettings streamSettings;
    private ProxySettings proxySettings;
    private Mux mux;

    public String getSendThrough() {
        return sendThrough;
    }

    public void setSendThrough(String sendThrough) {
        this.sendThrough = sendThrough;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public OutboundSettings getSettings() {
        return settings;
    }

    public void setSettings(OutboundSettings settings) {
        this.settings = settings;
    }

    public StreamSettings getStreamSettings() {
        return streamSettings;
    }

    public void setStreamSettings(StreamSettings streamSettings) {
        this.streamSettings = streamSettings;
    }

    public ProxySettings getProxySettings() {
        return proxySettings;
    }

    public void setProxySettings(ProxySettings proxySettings) {
        this.proxySettings = proxySettings;
    }

    public Mux getMux() {
        return mux;
    }

    public void setMux(Mux mux) {
        this.mux = mux;
    }
}
