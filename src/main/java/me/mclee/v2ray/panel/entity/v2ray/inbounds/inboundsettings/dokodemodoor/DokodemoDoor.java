package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.dokodemodoor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import com.google.protobuf.ByteString;
import com.v2ray.core.common.net.IPOrDomain;
import com.v2ray.core.proxy.dokodemo.Config;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.entity.v2ray.Network;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;

@Getter
@Setter
public class DokodemoDoor extends InboundSettings {
    private String address;
    private Integer port;
    private Network network;
    private Integer timeout;
    private Boolean followRedirect;
    private Integer userLevel;

    @Override
    public Config toGrpcType() throws AppException {
        IPOrDomain ip;
        try {
            InetAddress address = InetAddress.getByName(this.address);
            ByteString ipByteString = ByteString.copyFrom(address.getAddress());
            ip = IPOrDomain.newBuilder().setIp(ipByteString).build();
        } catch (UnknownHostException e) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }
        Config.Builder builder = Config.newBuilder();
        builder.setAddress(ip).setPort(this.port);
        if (this.network != null) {
            builder.addAllNetworks(this.network.toGRpcSet());
        }
        Optional.ofNullable(this.followRedirect).ifPresent(builder::setFollowRedirect);
        builder.setUserLevel(this.userLevel);
        return builder.build();
    }
}
