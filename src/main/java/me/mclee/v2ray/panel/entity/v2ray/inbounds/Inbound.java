package me.mclee.v2ray.panel.entity.v2ray.inbounds;

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
import com.v2ray.core.transport.internet.StreamConfig;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.utils.CommonUtils;
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
import me.mclee.v2ray.panel.entity.v2ray.inbounds.sniffing.DestOverride;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.sniffing.Sniffing;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.StreamSettings;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        // allocation
        AllocationStrategy allocationStrategy = null;
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
        }
        // TODO streamConfig 太复杂。。。
        StreamConfig streamConfig = StreamConfig.newBuilder().build();
        // sniffingConfig
        SniffingConfig sniffingSettings = null;
        if (sniffing != null) {
            Set<String> destOverrides = Optional.ofNullable(sniffing.getDestOverride())
                                                .map((s) -> s.stream().map(DestOverride::name)
                                                             .collect(Collectors.toSet()))
                                                .orElse(new HashSet<>());
            sniffingSettings = SniffingConfig.newBuilder().setEnabled(sniffing.getEnabled())
                                             .addAllDestinationOverride(destOverrides).build();
        }
        ReceiverConfig receiverConfig = ReceiverConfig.newBuilder().setPortRange(portRangeBuilder.build()).setListen(ip)
                                                      .setAllocationStrategy(allocationStrategy)
                                                      .setStreamSettings(streamConfig)
                                                      // 这个字段的含义？
                                                      .setReceiveOriginalDestination(false)
                                                      .setSniffingSettings(sniffingSettings).build();
        // proxySettings
        GeneratedMessageV3 proxySettings = this.getSettings().toGRpcType();
        return InboundHandlerConfig.newBuilder().setTag(this.getTag())
                                   .setReceiverSettings(CommonUtils.convertToTypedMessage(receiverConfig))
                                   .setProxySettings(CommonUtils.convertToTypedMessage(proxySettings)).build();
    }

}
