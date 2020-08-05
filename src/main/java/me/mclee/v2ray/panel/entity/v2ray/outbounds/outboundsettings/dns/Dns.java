package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.dns;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.google.protobuf.ByteString;
import com.v2ray.core.common.net.Endpoint;
import com.v2ray.core.common.net.IPOrDomain;
import com.v2ray.core.proxy.dns.Config;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;

@Setter
@Getter
public class Dns extends OutboundSettings {
    private Network network;
    private String address;
    private Integer port;

    @Override
    public Config toGrpcType() throws AppException {
        com.v2ray.core.common.net.Network endpointNetwork;
        switch (network) {
            case tcp:
                endpointNetwork = com.v2ray.core.common.net.Network.TCP;
                break;
            case udp:
                endpointNetwork = com.v2ray.core.common.net.Network.UDP;
                break;
            default:
                endpointNetwork = com.v2ray.core.common.net.Network.Unknown;
        }
        // listen ip
        IPOrDomain ip;
        try {
            InetAddress address = InetAddress.getByName(this.address);
            ByteString ipByteString = ByteString.copyFrom(address.getAddress());
            ip = IPOrDomain.newBuilder().setIp(ipByteString).build();
        } catch (UnknownHostException e) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }
        Endpoint endpoint = Endpoint.newBuilder().setNetwork(endpointNetwork).setAddress(ip).setPort(port).build();
        return Config.newBuilder().setServer(endpoint).build();
    }
}
