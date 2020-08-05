package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.mtproto;

import java.util.List;

import com.google.protobuf.ByteString;
import com.v2ray.core.common.serial.TypedMessage;
import com.v2ray.core.proxy.mtproto.Account;
import com.v2ray.core.proxy.mtproto.ServerConfig;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.utils.CommonUtils;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;
import org.springframework.util.CollectionUtils;

@Getter
@Setter
public class MTProto extends InboundSettings {
    private List<User> users;

    @Override
    public ServerConfig toGrpcType() {
        ServerConfig.Builder builder = ServerConfig.newBuilder();
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                TypedMessage account = CommonUtils
                        .convertToTypedMessage(Account.newBuilder().setSecret(ByteString.copyFromUtf8(user.getSecret()))
                                                      .build());
                com.v2ray.core.common.protocol.User.Builder userBuilder = com.v2ray.core.common.protocol.User
                        .newBuilder().setLevel(user.getLevel()).setEmail(user.getEmails()).setAccount(account);
                builder.addUser(userBuilder);
            }
        }
        return builder.build();
    }
}
