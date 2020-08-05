package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.socks;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import com.google.protobuf.ByteString;
import com.v2ray.core.common.net.IPOrDomain;
import com.v2ray.core.proxy.socks.AuthType;
import com.v2ray.core.proxy.socks.ServerConfig;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.entity.v2ray.Account;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;
import org.springframework.util.CollectionUtils;

@Getter
@Setter
public class Socks extends InboundSettings {
    private Auth auth;
    List<Account> accounts;
    private Boolean udp;
    private String ip;
    private Integer userLevel;

    @Override
    public ServerConfig toGrpcType() throws AppException {
        ServerConfig.Builder builder = ServerConfig.newBuilder();
        if (this.auth != null) {
            switch (this.auth) {
                case noauth:
                    builder.setAuthType(AuthType.NO_AUTH);
                    break;
                case password:
                    builder.setAuthType(AuthType.PASSWORD);
                    break;
                default:
            }
        }
        if (!CollectionUtils.isEmpty(this.accounts)) {
            for (Account account : this.accounts) {
                builder.putAccounts(account.getUser(), account.getPass());
            }
        }
        IPOrDomain ip;
        try {
            InetAddress address = InetAddress.getByName(this.ip);
            ByteString ipByteString = ByteString.copyFrom(address.getAddress());
            ip = IPOrDomain.newBuilder().setIp(ipByteString).build();
        } catch (UnknownHostException e) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }
        builder.setAddress(ip);
        builder.setUdpEnabled(this.udp);
        builder.setUserLevel(this.userLevel);
        return builder.build();
    }
}
