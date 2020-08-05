package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.socks;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import com.google.protobuf.ByteString;
import com.v2ray.core.common.net.IPOrDomain;
import com.v2ray.core.common.protocol.ServerEndpoint;
import com.v2ray.core.proxy.socks.Account;
import com.v2ray.core.proxy.socks.ClientConfig;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.utils.CommonUtils;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;
import org.springframework.util.CollectionUtils;

@Setter
@Getter
public class Socks extends OutboundSettings {
    private List<Server> servers;

    @Override
    public ClientConfig toGrpcType() throws AppException {
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
                List<User> users = server.getUsers();
                if (!CollectionUtils.isEmpty(users)) {
                    for (User user : users) {
                        com.v2ray.core.common.protocol.User.Builder socksUserBuilder = com.v2ray.core.common.protocol.User.newBuilder();
                        Optional.ofNullable(user.getLevel()).ifPresent(socksUserBuilder::setLevel);
                        String username = user.getUser();
                        String password = user.getPass();
                        Account account = Account.newBuilder().setUsername(username).setPassword(password).build();
                        socksUserBuilder.setAccount(CommonUtils.convertToTypedMessage(account));
                        serverEndpointBuilder.addUser(socksUserBuilder);
                    }
                }
                builder.addServer(serverEndpointBuilder);
            }
        }
        return builder.build();
    }
}
