package me.mclee.v2ray.panel.entity.v2ray.inbounds;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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

import java.io.IOException;

@JsonPropertyOrder({"protocol", "settings"})
public class Inbound {
    private String port;
    private String listen;
    private Protocol protocol;
    @JsonDeserialize(using = InboundSettingDeserializer.class)
    private InboundSettings settings;
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

    public InboundSettings getSettings() {
        return settings;
    }

    public void setSettings(InboundSettings settings) {
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

    public static class InboundSettingDeserializer extends JsonDeserializer<InboundSettings> {

        @Override
        public InboundSettings deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            InboundSettings inboundSetting = null;
            Inbound inbound = (Inbound) p.getParsingContext().getParent().getCurrentValue();
            if (inbound != null) {
                Protocol protocol = inbound.getProtocol();
                if (protocol != null) {
                    switch (protocol) {
                        case HTTP:
                            inboundSetting = ctxt.readValue(p, HTTP.class);
                            break;
                        case DokodemoDoor:
                            inboundSetting = ctxt.readValue(p, DokodemoDoor.class);
                            break;
                        case MTProto:
                            inboundSetting = ctxt.readValue(p, MTProto.class);
                            break;
                        case Shadowsocks:
                            inboundSetting = ctxt.readValue(p, Shadowsocks.class);
                            break;
                        case Socks:
                            inboundSetting = ctxt.readValue(p, Socks.class);
                            break;
                        case VMess:
                            inboundSetting = ctxt.readValue(p, VMess.class);
                            break;
                        default:
                            inboundSetting = null;
                    }
                }
            }
            return inboundSetting;
        }
    }
}
