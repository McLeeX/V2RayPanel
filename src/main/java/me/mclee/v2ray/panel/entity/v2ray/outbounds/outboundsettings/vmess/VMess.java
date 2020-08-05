package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.vmess;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import com.google.protobuf.ByteString;
import com.v2ray.core.common.net.IPOrDomain;
import com.v2ray.core.common.protocol.SecurityConfig;
import com.v2ray.core.common.protocol.SecurityType;
import com.v2ray.core.common.protocol.ServerEndpoint;
import com.v2ray.core.proxy.vmess.Account;
import com.v2ray.core.proxy.vmess.outbound.Config;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.utils.CommonUtils;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;
import org.springframework.util.CollectionUtils;

@Setter
@Getter
public class VMess extends OutboundSettings {
    private List<Vnext> vnext;

    @Override
    public Config toGrpcType() throws AppException {
        Config.Builder builder = Config.newBuilder();
        if (!CollectionUtils.isEmpty(vnext)) {
            for (Vnext vn : vnext) {
                ServerEndpoint.Builder serverEndpointBuilder = ServerEndpoint.newBuilder();
                // listen ip
                IPOrDomain ip;
                try {
                    InetAddress address = InetAddress.getByName(vn.getAddress());
                    ByteString ipByteString = ByteString.copyFrom(address.getAddress());
                    ip = IPOrDomain.newBuilder().setIp(ipByteString).build();
                } catch (UnknownHostException e) {
                    throw new AppException(ErrorCode.INTERNAL_ERROR);
                }
                serverEndpointBuilder.setAddress(ip);
                Integer port = vn.getPort();
                serverEndpointBuilder.setPort(port);
                List<User> users = vn.getUsers();
                if (!CollectionUtils.isEmpty(users)) {
                    for (User user : users) {
                        com.v2ray.core.common.protocol.User.Builder userBuilder = com.v2ray.core.common.protocol.User.newBuilder();
                        Optional.ofNullable(user.getLevel()).ifPresent(userBuilder::setLevel);
                        Account.Builder accountBuilder = Account.newBuilder();
                        String uuid = user.getId().toString();
                        accountBuilder.setId(uuid);
                        Integer alterId = user.getAlterId();
                        accountBuilder.setAlterId(alterId);
                        Security security = Optional.ofNullable(user.getSecurity()).orElse(Security.auto);
                        SecurityType securityType;
                        switch (security) {
                            case chacha20Poly1305:
                                securityType = SecurityType.CHACHA20_POLY1305;
                                break;
                            case aes128Gcm:
                                securityType = SecurityType.AES128_GCM;
                                break;
                            case none:
                                securityType = SecurityType.NONE;
                                break;
                            case auto:
                            default:
                                securityType = SecurityType.AUTO;
                        }
                        SecurityConfig securityConfig = SecurityConfig.newBuilder().setType(securityType).build();
                        accountBuilder.setSecuritySettings(securityConfig);
                        userBuilder.setAccount(CommonUtils.convertToTypedMessage(accountBuilder.build()));
                        serverEndpointBuilder.addUser(userBuilder);
                    }
                }
                builder.addReceiver(serverEndpointBuilder);
            }
        }
        return builder.build();
    }
}
