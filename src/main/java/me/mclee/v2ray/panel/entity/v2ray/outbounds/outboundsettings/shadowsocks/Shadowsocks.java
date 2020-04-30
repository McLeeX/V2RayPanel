package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.shadowsocks;

import com.google.protobuf.ByteString;
import com.v2ray.core.common.net.IPOrDomain;
import com.v2ray.core.common.protocol.ServerEndpoint;
import com.v2ray.core.common.protocol.User;
import com.v2ray.core.proxy.shadowsocks.Account;
import com.v2ray.core.proxy.shadowsocks.CipherType;
import com.v2ray.core.proxy.shadowsocks.ClientConfig;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.utils.CommonUtils;
import me.mclee.v2ray.panel.entity.v2ray.Method;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;
import org.springframework.util.CollectionUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class Shadowsocks extends OutboundSettings {
    private List<Server> servers;

    @Override
    public ClientConfig toGRpcType() throws AppException {
        ClientConfig.Builder builder = ClientConfig.newBuilder();
        if (!CollectionUtils.isEmpty(servers)) {
            for (Server server : servers) {
                ServerEndpoint.Builder serverEndpointBuilder = ServerEndpoint.newBuilder();
                // listen ip
                IPOrDomain ip;
                try {
                    InetAddress address = InetAddress.getByName(server.getAddress());
                    ByteString ipByteString = ByteString.copyFrom(address.getAddress());
                    ip = IPOrDomain.newBuilder().setIp(ipByteString).build();
                } catch (UnknownHostException e) {
                    throw new AppException(ErrorCode.INTERNAL_ERROR);
                }
                serverEndpointBuilder.setAddress(ip);
                Integer port = server.getPort();
                serverEndpointBuilder.setPort(port);
                User.Builder userBuilder = User.newBuilder();
                Optional.ofNullable(server.getEmail()).ifPresent(userBuilder::setEmail);
                Optional.ofNullable(server.getLevel()).ifPresent(userBuilder::setLevel);
                Account.Builder accountBuilder = Account.newBuilder();
                Method method = server.getMethod();
                CipherType cipherType;
                switch (method) {
                    case aes_128_cfb:
                        cipherType = CipherType.AES_128_CFB;
                        break;
                    case aes_128_gcm:
                        cipherType = CipherType.AES_128_GCM;
                        break;
                    case aes_256_cfb:
                        cipherType = CipherType.AES_256_CFB;
                        break;
                    case aes_256_gcm:
                        cipherType = CipherType.AES_256_GCM;
                        break;
                    case chacha20:
                        cipherType = CipherType.CHACHA20;
                        break;
                    case chacha20_ietf:
                        cipherType = CipherType.CHACHA20_IETF;
                        break;
                    case chacha20_poly1305:
                    case chacha20_ietf_poly1305:
                    default:
                        cipherType = CipherType.CHACHA20_POLY1305;
                        break;
                }
                accountBuilder.setCipherType(cipherType);
                Boolean ota = server.getOta();
                Account.OneTimeAuth oneTimeAuth;
                if (ota == null) {
                    oneTimeAuth = Account.OneTimeAuth.Auto;
                } else if (ota) {
                    oneTimeAuth = Account.OneTimeAuth.Enabled;
                } else {
                    oneTimeAuth = Account.OneTimeAuth.Disabled;
                }
                accountBuilder.setOta(oneTimeAuth);
                String password = server.getPassword();
                accountBuilder.setPassword(password);
                userBuilder.setAccount(CommonUtils.convertToTypedMessage(accountBuilder.build()));
                serverEndpointBuilder.addUser(userBuilder);
                builder.addServer(serverEndpointBuilder);
            }
        }
        return builder.build();
    }
}
