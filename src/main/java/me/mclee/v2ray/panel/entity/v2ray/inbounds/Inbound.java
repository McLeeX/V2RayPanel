package me.mclee.v2ray.panel.entity.v2ray.inbounds;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import me.mclee.v2ray.panel.common.utils.JsonUtil;
import me.mclee.v2ray.panel.entity.v2ray.Protocol;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.allocate.Allocate;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.dokodemodoor.DokodemoDoor;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.http.HTTP;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.mtproto.MTProto;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.shadowsocks.Shadowsocks;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.socks.Socks;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.vmess.VMess;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.sniffing.Sniffing;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.StreamSettings;

public class Inbound {
    private String port;
    private String listen;
    private Protocol protocol;
    private JsonNode settings;
    private StreamSettings streamSettings;
    private String tag;
    private Sniffing sniffing;
    private Allocate allocate;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getListen() {
        return listen;
    }

    public void setListen(String listen) {
        this.listen = listen;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    @JsonGetter("settings")
    public JsonNode getSettingsJsonNode() {
        return settings;
    }

    public InboundSettings getSettings() {
        InboundSettings inboundSetting = null;
        if (protocol != null && settings != null) {
            String sSettings = settings.toString();
            switch (protocol) {
                case HTTP:
                    inboundSetting = JsonUtil.string2Obj(sSettings, HTTP.class);
                    break;
                case DokodemoDoor:
                    inboundSetting = JsonUtil.string2Obj(sSettings, DokodemoDoor.class);
                    break;
                case MTProto:
                    inboundSetting = JsonUtil.string2Obj(sSettings, MTProto.class);
                    break;
                case Shadowsocks:
                    inboundSetting = JsonUtil.string2Obj(sSettings, Shadowsocks.class);
                    break;
                case Socks:
                    inboundSetting = JsonUtil.string2Obj(sSettings, Socks.class);
                    break;
                case VMess:
                    inboundSetting = JsonUtil.string2Obj(sSettings, VMess.class);
                    break;
                default:
                    inboundSetting = null;
            }
        }
        return inboundSetting;
    }

    @JsonSetter("settings")
    public void setSettings(JsonNode settings) {
        this.settings = settings;
    }

    public StreamSettings getStreamSettings() {
        return streamSettings;
    }

    public void setStreamSettings(StreamSettings streamSettings) {
        this.streamSettings = streamSettings;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Sniffing getSniffing() {
        return sniffing;
    }

    public void setSniffing(Sniffing sniffing) {
        this.sniffing = sniffing;
    }

    public Allocate getAllocate() {
        return allocate;
    }

    public void setAllocate(Allocate allocate) {
        this.allocate = allocate;
    }

}
