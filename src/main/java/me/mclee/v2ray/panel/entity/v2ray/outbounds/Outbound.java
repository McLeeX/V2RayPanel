package me.mclee.v2ray.panel.entity.v2ray.outbounds;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageV3;
import com.v2ray.core.OutboundHandlerConfig;
import com.v2ray.core.app.proxyman.MultiplexingConfig;
import com.v2ray.core.app.proxyman.SenderConfig;
import com.v2ray.core.common.net.IPOrDomain;
import com.v2ray.core.common.protocol.SecurityConfig;
import com.v2ray.core.common.protocol.SecurityType;
import com.v2ray.core.common.serial.TypedMessage;
import com.v2ray.core.transport.internet.ProxyConfig;
import com.v2ray.core.transport.internet.SocketConfig;
import com.v2ray.core.transport.internet.StreamConfig;
import com.v2ray.core.transport.internet.TransportConfig;
import com.v2ray.core.transport.internet.kcp.*;
import com.v2ray.core.transport.internet.tls.Config;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.utils.JsonUtils;
import me.mclee.v2ray.panel.entity.v2ray.Protocol;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.mux.Mux;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.blackhole.Blackhole;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.dns.Dns;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.freedom.Freedom;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.http.HTTP;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.mtproto.MTProto;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.shadowsocks.Shadowsocks;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.socks.Socks;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.vmess.VMess;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.proxysettings.ProxySettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.*;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.common.Header;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.common.Type;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.domainsocket.DsSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.http.HttpSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.kcp.KcpSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.quic.QuicSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.tls.TlsSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.websocket.WsSettings;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                    outboundSetting = JsonUtils.string2Obj(sSettings, Blackhole.class);
                    break;
                case DNS:
                    outboundSetting = JsonUtils.string2Obj(sSettings, Dns.class);
                    break;
                case Freedom:
                    outboundSetting = JsonUtils.string2Obj(sSettings, Freedom.class);
                    break;
                case HTTP:
                    outboundSetting = JsonUtils.string2Obj(sSettings, HTTP.class);
                    break;
                case MTProto:
                    outboundSetting = JsonUtils.string2Obj(sSettings, MTProto.class);
                    break;
                case Shadowsocks:
                    outboundSetting = JsonUtils.string2Obj(sSettings, Shadowsocks.class);
                    break;
                case Socks:
                    outboundSetting = JsonUtils.string2Obj(sSettings, Socks.class);
                    break;
                case VMess:
                    outboundSetting = JsonUtils.string2Obj(sSettings, VMess.class);
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

    public OutboundHandlerConfig toOutboundHandlerConfig() throws AppException {
        SenderConfig.Builder senderConfigBuilder = SenderConfig.newBuilder();
        // sendThrough
        IPOrDomain ip;
        try {
            InetAddress address = InetAddress.getByName(this.sendThrough);
            ByteString ipByteString = ByteString.copyFrom(address.getAddress());
            ip = IPOrDomain.newBuilder().setIp(ipByteString).build();
        } catch (UnknownHostException e) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }
        senderConfigBuilder.setVia(ip);
        // streamConfig
        StreamConfig streamConfig;
        if (streamSettings != null) {
            Network network = streamSettings.getNetwork();
            StreamConfig.Builder streamConfigBuilder = StreamConfig.newBuilder();
            streamConfigBuilder.setProtocolName(network.name());
            // tlsSettings
            Security security = streamSettings.getSecurity();
            TlsSettings tlsSettings = streamSettings.getTlsSettings();
            if (security == Security.tls && tlsSettings != null) {
                streamConfigBuilder.setSecurityType(security.name());
                Config.Builder tlsConfigBuilder = Config.newBuilder();
                boolean allowInsecure = Optional.ofNullable(tlsSettings.getAllowInsecure()).orElse(false);
                String serverName = tlsSettings.getServerName();
                List<String> alpn = tlsSettings.getAlpn();
                boolean disableSystemRoot = Optional.ofNullable(tlsSettings.getDisableSystemRoot()).orElse(false);
                List<Certificate> certificates = tlsSettings.getCertificates();
                tlsConfigBuilder.setAllowInsecure(allowInsecure).setAllowInsecureCiphers(allowInsecure)
                        .setServerName(serverName).setDisableSessionResumption(false).setDisableSystemRoot(disableSystemRoot);
                if (!CollectionUtils.isEmpty(certificates)) {
                    for (Certificate certificate : certificates) {
                        // TODO 证书配置
                    }
                }
                if (!CollectionUtils.isEmpty(alpn)) {
                    alpn.forEach(tlsConfigBuilder::addNextProtocol);
                }
                streamConfigBuilder.addSecuritySettings(convertToTypedMessage(tlsConfigBuilder.build()));
            }
            // kcpSettings
            KcpSettings kcpSettings = streamSettings.getKcpSettings();
            if (kcpSettings != null) {
                Integer mtu = kcpSettings.getMtu();
                Integer tti = kcpSettings.getTti();
                Integer uplinkCapacity = kcpSettings.getUplinkCapacity();
                Integer downlinkCapacity = kcpSettings.getDownlinkCapacity();
                boolean congestion = Optional.ofNullable(kcpSettings.getCongestion()).orElse(false);
                Integer writeBufferSize = kcpSettings.getWriteBufferSize();
                Integer readBufferSize = kcpSettings.getReadBufferSize();
                com.v2ray.core.transport.internet.kcp.Config.Builder kcpConfigBuilder = com.v2ray.core.transport.internet.kcp.Config.newBuilder();
                if (mtu != null) {
                    kcpConfigBuilder.setMtu(MTU.newBuilder().setValue(mtu));
                }
                if (tti != null) {
                    kcpConfigBuilder.setTti(TTI.newBuilder().setValue(tti));
                }
                if (uplinkCapacity != null) {
                    kcpConfigBuilder.setUplinkCapacity(UplinkCapacity.newBuilder().setValue(uplinkCapacity));
                }
                if (downlinkCapacity != null) {
                    kcpConfigBuilder.setDownlinkCapacity(DownlinkCapacity.newBuilder().setValue(downlinkCapacity));
                }
                kcpConfigBuilder.setCongestion(congestion);
                if (writeBufferSize != null) {
                    kcpConfigBuilder.setWriteBuffer(WriteBuffer.newBuilder().setSize(writeBufferSize));
                }
                if (readBufferSize != null) {
                    kcpConfigBuilder.setReadBuffer(ReadBuffer.newBuilder().setSize(readBufferSize));
                }
                Header header = kcpSettings.getHeader();
                Type headerType = header.getType();
                if (headerType != null) {
                    switch (headerType) {
                        case utp:
                            com.v2ray.core.transport.internet.headers.utp.Config utpConfig =
                                    com.v2ray.core.transport.internet.headers.utp.Config.newBuilder().build();
                            kcpConfigBuilder.setHeaderConfig(convertToTypedMessage(utpConfig));
                            break;
                        case dtls:
                            com.v2ray.core.transport.internet.headers.tls.PacketConfig dtlsConfig =
                                    com.v2ray.core.transport.internet.headers.tls.PacketConfig.newBuilder().build();
                            kcpConfigBuilder.setHeaderConfig(convertToTypedMessage(dtlsConfig));
                            break;
                        case srtp:
                            com.v2ray.core.transport.internet.headers.srtp.Config srtpConfig =
                                    com.v2ray.core.transport.internet.headers.srtp.Config.newBuilder().build();
                            kcpConfigBuilder.setHeaderConfig(convertToTypedMessage(srtpConfig));
                            break;
                        case wireguard:
                            com.v2ray.core.transport.internet.headers.wireguard.WireguardConfig wireguardConfig =
                                    com.v2ray.core.transport.internet.headers.wireguard.WireguardConfig.newBuilder().build();
                            kcpConfigBuilder.setHeaderConfig(convertToTypedMessage(wireguardConfig));
                            break;
                        case wechatVideo:
                            com.v2ray.core.transport.internet.headers.wechat.VideoConfig wechatVidepConfig =
                                    com.v2ray.core.transport.internet.headers.wechat.VideoConfig.newBuilder().build();
                            kcpConfigBuilder.setHeaderConfig(convertToTypedMessage(wechatVidepConfig));
                            break;
                        case none:
                        default:
                    }
                }
                streamConfigBuilder.addTransportSettings(TransportConfig.newBuilder().setProtocolName(Network.kcp.name())
                        .setSettings(convertToTypedMessage(kcpConfigBuilder.build())).build());

            }
            // wsSettings
            WsSettings wsSettings = streamSettings.getWsSettings();
            if (wsSettings != null) {
                String path = wsSettings.getPath();
                Map<String, String> headers = wsSettings.getHeaders();
                com.v2ray.core.transport.internet.websocket.Config.Builder wsConfigBuilder =
                        com.v2ray.core.transport.internet.websocket.Config.newBuilder();
                if (!StringUtils.isEmpty(path)) {
                    wsConfigBuilder.setPath(path);
                }
                if (!CollectionUtils.isEmpty(headers)) {
                    headers.forEach((key, value) -> wsConfigBuilder.addHeader(com.v2ray.core.transport.internet.websocket.Header.newBuilder().setKey(key).setValue(value)));
                }
                streamConfigBuilder.addTransportSettings(TransportConfig.newBuilder().setProtocolName(Network.ws.name())
                        .setSettings(convertToTypedMessage(wsConfigBuilder.build())).build());
            }
            // httpSettings
            HttpSettings httpSettings = streamSettings.getHttpSettings();
            if (httpSettings != null) {
                List<String> hosts = httpSettings.getHost();
                String path = httpSettings.getPath();
                com.v2ray.core.transport.internet.http.Config.Builder httpConfigBuilder =
                        com.v2ray.core.transport.internet.http.Config.newBuilder();
                if (!StringUtils.isEmpty(path)) {
                    httpConfigBuilder.setPath(path);
                }
                if (!CollectionUtils.isEmpty(hosts)) {
                    hosts.forEach(httpConfigBuilder::addHost);
                }
                streamConfigBuilder.addTransportSettings(TransportConfig.newBuilder().setProtocolName(Network.http.name())
                        .setSettings(convertToTypedMessage(httpConfigBuilder.build())).build());
            }
            // dsSettings
            DsSettings dsSettings = streamSettings.getDsSettings();
            if (dsSettings != null) {
                com.v2ray.core.transport.internet.domainsocket.Config dsConfig =
                        com.v2ray.core.transport.internet.domainsocket.Config.newBuilder().setPath(dsSettings.getPath()).build();
                streamConfigBuilder.addTransportSettings(TransportConfig.newBuilder().setProtocolName(Network.domainsocket.name())
                        .setSettings(convertToTypedMessage(dsConfig)).build());
            }
            // quicSettings
            QuicSettings quicSettings = streamSettings.getQuicSettings();
            if (quicSettings != null) {
                String key = quicSettings.getKey();
                com.v2ray.core.transport.internet.quic.Config.Builder quicConfigBuilder =
                        com.v2ray.core.transport.internet.quic.Config.newBuilder();
                quicConfigBuilder.setKey(key);
                if (quicSettings.getSecurity() != null) {
                    SecurityType securityType;
                    switch (quicSettings.getSecurity()) {
                        case aes128Gcm:
                            securityType = SecurityType.AES128_GCM;
                            break;
                        case chacha20Poly1305:
                            securityType = SecurityType.CHACHA20_POLY1305;
                            break;
                        case none:
                        default:
                            securityType = SecurityType.NONE;
                    }
                    quicConfigBuilder.setSecurity(SecurityConfig.newBuilder().setType(securityType).build());
                }
                Header header = quicSettings.getHeader();
                Type headerType = header.getType();
                if (headerType != null) {
                    switch (headerType) {
                        case utp:
                            com.v2ray.core.transport.internet.headers.utp.Config utpConfig =
                                    com.v2ray.core.transport.internet.headers.utp.Config.newBuilder().build();
                            quicConfigBuilder.setHeader(convertToTypedMessage(utpConfig));
                            break;
                        case dtls:
                            com.v2ray.core.transport.internet.headers.tls.PacketConfig dtlsConfig =
                                    com.v2ray.core.transport.internet.headers.tls.PacketConfig.newBuilder().build();
                            quicConfigBuilder.setHeader(convertToTypedMessage(dtlsConfig));
                            break;
                        case srtp:
                            com.v2ray.core.transport.internet.headers.srtp.Config srtpConfig =
                                    com.v2ray.core.transport.internet.headers.srtp.Config.newBuilder().build();
                            quicConfigBuilder.setHeader(convertToTypedMessage(srtpConfig));
                            break;
                        case wireguard:
                            com.v2ray.core.transport.internet.headers.wireguard.WireguardConfig wireguardConfig =
                                    com.v2ray.core.transport.internet.headers.wireguard.WireguardConfig.newBuilder().build();
                            quicConfigBuilder.setHeader(convertToTypedMessage(wireguardConfig));
                            break;
                        case wechatVideo:
                            com.v2ray.core.transport.internet.headers.wechat.VideoConfig wechatVidepConfig =
                                    com.v2ray.core.transport.internet.headers.wechat.VideoConfig.newBuilder().build();
                            quicConfigBuilder.setHeader(convertToTypedMessage(wechatVidepConfig));
                            break;
                        case none:
                        default:
                    }
                }
                streamConfigBuilder.addTransportSettings(TransportConfig.newBuilder().setProtocolName(Network.quic.name())
                        .setSettings(convertToTypedMessage(quicConfigBuilder.build())).build());
            }
            // sockopt
            Sockopt sockopt = streamSettings.getSockopt();
            if (sockopt != null) {
                Integer mark = sockopt.getMark();
                boolean tcpFastOpen = Optional.ofNullable(sockopt.getTcpFastOpen()).orElse(false);
                String tproxy = sockopt.getTproxy();
                SocketConfig.TCPFastOpenState tcpFastOpenState = tcpFastOpen ? SocketConfig.TCPFastOpenState.Enable : SocketConfig.TCPFastOpenState.Disable;
                SocketConfig.TProxyMode tProxyMode;
                if ("off".equals(tproxy)) {
                    tProxyMode = SocketConfig.TProxyMode.TProxy;
                } else {
                    tProxyMode = SocketConfig.TProxyMode.Off;
                }
                SocketConfig socketConfig = SocketConfig.newBuilder().setMark(mark).setTfo(tcpFastOpenState).setTproxy(tProxyMode).build();
                streamConfigBuilder.setSocketSettings(socketConfig);
            }
            streamConfig = streamConfigBuilder.build();
            senderConfigBuilder.setStreamSettings(streamConfig);
        }
        if (proxySettings != null) {
            String proxySettingsTag = proxySettings.getTag();
            ProxyConfig proxyConfig = ProxyConfig.newBuilder().setTag(proxySettingsTag).build();
            senderConfigBuilder.setProxySettings(proxyConfig);
        }
        // mux
        if (mux != null) {
            boolean enabled = Optional.ofNullable(mux.getEnabled()).orElse(false);
            int concurrency = Optional.ofNullable(mux.getConcurrency()).orElse(1);
            MultiplexingConfig multiplexingConfig = MultiplexingConfig.newBuilder().setEnabled(enabled).setConcurrency(concurrency).build();
            senderConfigBuilder.setMultiplexSettings(multiplexingConfig);
        }
        // proxySettings
        GeneratedMessageV3 proxySettings = this.getSettings().toGRpcType();
        return OutboundHandlerConfig.newBuilder().setTag(tag)
                .setSenderSettings(convertToTypedMessage(senderConfigBuilder.build()))
                .setProxySettings(convertToTypedMessage(proxySettings)).build();
    }

    /**
     * 转化需要的结构体为TypedMessage
     *
     * @param message V2RayApi proto 结构体
     * @return TypedMessage
     */
    private TypedMessage convertToTypedMessage(GeneratedMessageV3 message) {
        return TypedMessage.newBuilder()
                .setType(message.getDescriptorForType().getFullName())
                .setValue(message.toByteString()).build();
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
                            outboundSetting = ctxt.readValue(p, HTTP.class);
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
