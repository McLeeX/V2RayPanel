package me.mclee.v2ray.panel.entity.v2ray.outbounds;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import me.mclee.v2ray.panel.common.utils.JsonUtil;
import me.mclee.v2ray.panel.entity.v2ray.Protocol;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.mux.Mux;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.blackhole.Blackhole;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.dns.Dns;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.freedom.Freedom;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.http.Http;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.mtproto.MTProto;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.shadowsocks.Shadowsocks;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.socks.Socks;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.vmess.VMess;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.proxysettings.ProxySettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.StreamSettings;

import java.io.IOException;

public class Outbound {
    private String sendThrough;
    private Protocol protocol;
    private JsonNode settings;
    private String tag;
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

    @JsonGetter("settings")
    public JsonNode getSettingsJsonNode() {
        return settings;
    }

    public OutboundSettings getSettings() {
        OutboundSettings outboundSetting = null;
        if (protocol != null && settings != null) {
            String sSettings = settings.toString();
            switch (protocol) {
                case Blackhole:
                    outboundSetting = JsonUtil.string2Obj(sSettings, Blackhole.class);
                    break;
                case DNS:
                    outboundSetting = JsonUtil.string2Obj(sSettings, Dns.class);
                    break;
                case Freedom:
                    outboundSetting = JsonUtil.string2Obj(sSettings, Freedom.class);
                    break;
                case HTTP:
                    outboundSetting = JsonUtil.string2Obj(sSettings, Http.class);
                    break;
                case MTProto:
                    outboundSetting = JsonUtil.string2Obj(sSettings, MTProto.class);
                    break;
                case Shadowsocks:
                    outboundSetting = JsonUtil.string2Obj(sSettings, Shadowsocks.class);
                    break;
                case Socks:
                    outboundSetting = JsonUtil.string2Obj(sSettings, Socks.class);
                    break;
                case VMess:
                    outboundSetting = JsonUtil.string2Obj(sSettings, VMess.class);
                    break;
                default:
                    outboundSetting = null;
            }
        }
        return outboundSetting;
    }

    @JsonSetter("settings")
    public void setSettings(JsonNode settings) {
        this.settings = settings;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public static class OutboundSettingDeserializer extends JsonDeserializer<OutboundSettings> {

        @Override
        public OutboundSettings deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            OutboundSettings outboundSetting = null;
            Outbound outbound = (Outbound) p.getParsingContext().getParent().getCurrentValue();
            if (outbound != null) {
                Protocol protocol = outbound.getProtocol();
                if (protocol != null) {
                    switch (protocol) {
                        case Blackhole:
                            outboundSetting = ctxt.readValue(p, Blackhole.class);
                            break;
                        case DNS:
                            outboundSetting = ctxt.readValue(p, Dns.class);
                            break;
                        case Freedom:
                            outboundSetting = ctxt.readValue(p, Freedom.class);
                            break;
                        case HTTP:
                            outboundSetting = ctxt.readValue(p, Http.class);
                            break;
                        case MTProto:
                            outboundSetting = ctxt.readValue(p, MTProto.class);
                            break;
                        case Shadowsocks:
                            outboundSetting = ctxt.readValue(p, Shadowsocks.class);
                            break;
                        case Socks:
                            outboundSetting = ctxt.readValue(p, Socks.class);
                            break;
                        case VMess:
                            outboundSetting = ctxt.readValue(p, VMess.class);
                            break;
                        default:
                            outboundSetting = null;
                    }
                }
            }
            return outboundSetting;
        }
    }
}
