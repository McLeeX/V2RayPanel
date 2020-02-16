package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.vmess;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.v2ray.core.common.protocol.User;
import com.v2ray.core.proxy.vmess.Account;
import com.v2ray.core.proxy.vmess.inbound.Config;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.utils.CommonUtils;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class VMess extends InboundSettings {
    private List<Client> clients;
    @JsonProperty("default")
    private Default defaultConfig;
    private Detour detour;
    private Boolean disableInsecureEncryption;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Default getDefaultConfig() {
        return defaultConfig;
    }

    public void setDefaultConfig(Default defaultConfig) {
        this.defaultConfig = defaultConfig;
    }

    public Detour getDetour() {
        return detour;
    }

    public void setDetour(Detour detour) {
        this.detour = detour;
    }

    public Boolean getDisableInsecureEncryption() {
        return disableInsecureEncryption;
    }

    public void setDisableInsecureEncryption(Boolean disableInsecureEncryption) {
        this.disableInsecureEncryption = disableInsecureEncryption;
    }

    @Override
    public Config toGRpcType() throws AppException {
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
