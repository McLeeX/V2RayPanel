package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.http;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import com.google.protobuf.ByteString;
import com.v2ray.core.common.net.IPOrDomain;
import com.v2ray.core.common.protocol.ServerEndpoint;
import com.v2ray.core.common.protocol.User;
import com.v2ray.core.proxy.http.ClientConfig;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.utils.CommonUtils;
import me.mclee.v2ray.panel.entity.v2ray.Account;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;
import org.springframework.util.CollectionUtils;

@Setter
@Getter
public class HTTP extends OutboundSettings {
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
                List<Account> users = server.getUsers();
                if (!CollectionUtils.isEmpty(users)) {
                    for (Account user : users) {
                        com.v2ray.core.proxy.http.Account account = com.v2ray.core.proxy.http.Account.newBuilder()
                                .setUsername(user.getUser()).setPassword(user.getPass()).build();
                        User httpUser = User.newBuilder().setAccount(CommonUtils.convertToTypedMessage(account)).build();
                        serverEndpointBuilder.addUser(httpUser);
                    }
                }
                builder.addServer(serverEndpointBuilder);
            }
        }
        return builder.build();
    }
}
