package me.mclee.v2ray.panel.entity.v2ray.inbounds;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageV3;
import com.v2ray.core.InboundHandlerConfig;
import com.v2ray.core.app.proxyman.AllocationStrategy;
import com.v2ray.core.app.proxyman.ReceiverConfig;
import com.v2ray.core.app.proxyman.SniffingConfig;
import com.v2ray.core.common.net.IPOrDomain;
import com.v2ray.core.common.net.PortRange;
import com.v2ray.core.common.protocol.SecurityConfig;
import com.v2ray.core.common.protocol.SecurityType;
import com.v2ray.core.common.serial.TypedMessage;
import com.v2ray.core.transport.internet.SocketConfig;
import com.v2ray.core.transport.internet.StreamConfig;
import com.v2ray.core.transport.internet.TransportConfig;
import com.v2ray.core.transport.internet.kcp.DownlinkCapacity;
import com.v2ray.core.transport.internet.kcp.MTU;
import com.v2ray.core.transport.internet.kcp.ReadBuffer;
import com.v2ray.core.transport.internet.kcp.TTI;
import com.v2ray.core.transport.internet.kcp.UplinkCapacity;
import com.v2ray.core.transport.internet.kcp.WriteBuffer;
import com.v2ray.core.transport.internet.tls.Config;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.utils.CommonUtils;
import me.mclee.v2ray.panel.common.utils.JsonUtils;
import me.mclee.v2ray.panel.entity.v2ray.Protocol;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.allocate.Allocate;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.dokodemodoor.DokodemoDoor;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.http.HTTP;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.mtproto.MTProto;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.shadowsocks.Shadowsocks;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.socks.Socks;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.vmess.VMess;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.sniffing.DestOverride;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.sniffing.Sniffing;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.Certificate;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.Network;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.Security;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.Sockopt;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.StreamSettings;
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
                    inboundSetting = JsonUtils.string2Obj(sSettings, HTTP.class);
                    break;
                case DokodemoDoor:
                    inboundSetting = JsonUtils.string2Obj(sSettings, DokodemoDoor.class);
                    break;
                case MTProto:
                    inboundSetting = JsonUtils.string2Obj(sSettings, MTProto.class);
                    break;
                case Shadowsocks:
                    inboundSetting = JsonUtils.string2Obj(sSettings, Shadowsocks.class);
                    break;
                case Socks:
                    inboundSetting = JsonUtils.string2Obj(sSettings, Socks.class);
                    break;
                case VMess:
                    inboundSetting = JsonUtils.string2Obj(sSettings, VMess.class);
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

    public InboundHandlerConfig toInboundHandlerConfig() throws AppException {
        // listen port/ports
        PortRange.Builder portRangeBuilder = PortRange.newBuilder();
        String[] ports = port.split("-");
        switch (ports.length) {
            case 1:
                int singlePort = Integer.parseInt(ports[0]);
                portRangeBuilder.setFrom(singlePort).setTo(singlePort);
                break;
            case 2:
                int from = Integer.parseInt(ports[0]);
                int to = Integer.parseInt(ports[1]);
                portRangeBuilder.setFrom(from).setTo(to);
                break;
            default:
                throw new AppException(ErrorCode.INTERNAL_ERROR);
        }
        // listen ip
        IPOrDomain ip;
        try {
            InetAddress address = InetAddress.getByName(this.listen);
            ByteString ipByteString = ByteString.copyFrom(address.getAddress());
            ip = IPOrDomain.newBuilder().setIp(ipByteString).build();
        } catch (UnknownHostException e) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }
        ReceiverConfig.Builder receiverConfigBuilder = ReceiverConfig.newBuilder().setPortRange(portRangeBuilder.build()).setListen(ip)
                // 这个字段的含义？
                .setReceiveOriginalDestination(false);
        // allocation
        AllocationStrategy allocationStrategy;
        if (allocate != null) {
            // allocation.type
            AllocationStrategy.Type type;
            switch (allocate.getStrategy()) {
                case always:
                    type = AllocationStrategy.Type.Always;
                    break;
                case random:
                    type = AllocationStrategy.Type.Random;
                    break;
                default:
                    type = null;
            }
            // allocation.concurrency
            AllocationStrategy.AllocationStrategyConcurrency concurrency = null;
            if (allocate.getStrategy() != null) {
                concurrency = AllocationStrategy.AllocationStrategyConcurrency
                        .newBuilder().setValue(allocate.getConcurrency()).build();
            }
            // allocation.refresh
            AllocationStrategy.AllocationStrategyRefresh refresh = null;
            if (allocate.getRefresh() != null) {
                refresh = AllocationStrategy.AllocationStrategyRefresh
                        .newBuilder().setValue(allocate.getRefresh()).build();
            }
            allocationStrategy = AllocationStrategy.newBuilder().setType(type).setConcurrency(concurrency)
                    .setRefresh(refresh).build();
            receiverConfigBuilder.setAllocationStrategy(allocationStrategy);
        }
        // streamConfig
        StreamConfig streamConfig;
        if (streamSettings != null) {
            Network network = streamSettings.getNetwork();
            StreamConfig.Builder streamConfigBuilder = StreamConfig.newBuilder().setProtocolName(network.name());
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
            receiverConfigBuilder.setStreamSettings(streamConfig);
        }
        // sniffingConfig
        SniffingConfig sniffingSettings;
        if (sniffing != null) {
            Set<String> destOverrides = Optional.ofNullable(sniffing.getDestOverride())
                    .map((s) -> s.stream().map(DestOverride::name)
                            .collect(Collectors.toSet()))
                    .orElse(new HashSet<>());
            sniffingSettings = SniffingConfig.newBuilder().setEnabled(sniffing.getEnabled())
                    .addAllDestinationOverride(destOverrides).build();
            receiverConfigBuilder.setSniffingSettings(sniffingSettings);
        }
        ReceiverConfig receiverConfig = receiverConfigBuilder.build();
        // proxySettings
        GeneratedMessageV3 proxySettings = this.getSettings().toGrpcType();
        return InboundHandlerConfig.newBuilder().setTag(this.getTag())
                .setReceiverSettings(CommonUtils.convertToTypedMessage(receiverConfig))
                .setProxySettings(CommonUtils.convertToTypedMessage(proxySettings)).build();
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
}
