package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.vmess;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.v2ray.core.common.protocol.User;
import com.v2ray.core.proxy.vmess.Account;
import com.v2ray.core.proxy.vmess.inbound.Config;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.utils.CommonUtils;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;
import org.springframework.util.CollectionUtils;

@Setter
@Getter
public class VMess extends InboundSettings {
    private List<Client> clients;
    @JsonProperty("default")
    private Default defaultConfig;
    private Detour detour;
    private Boolean disableInsecureEncryption;

    @Override
    public Config toGrpcType() throws AppException {
        Config.Builder builder = Config.newBuilder();
        if (!CollectionUtils.isEmpty(this.clients)) {
            for (Client client : this.clients) {
                User.Builder userBuilder = User.newBuilder();
                userBuilder.setLevel(client.getLevel());
                userBuilder.setEmail(client.getEmail());
                Account account = Account.newBuilder().setId(client.getId().toString())
                                         .setAlterId(client.getAlterId()).build();
                userBuilder.setAccount(CommonUtils.convertToTypedMessage(account));
                builder.addUser(userBuilder.build());
            }
        }
        return builder.build();
    }
}
