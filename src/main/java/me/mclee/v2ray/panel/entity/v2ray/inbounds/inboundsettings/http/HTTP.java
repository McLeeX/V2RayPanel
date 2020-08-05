package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.http;

import java.util.List;

import com.v2ray.core.proxy.http.ServerConfig;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.entity.v2ray.Account;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;
import org.springframework.util.CollectionUtils;

@Getter
@Setter
public class HTTP extends InboundSettings {
    private Integer timeout;
    private List<Account> accounts;
    private Boolean allowTransparent;
    private Integer userLevel;

    @Override
    public ServerConfig toGrpcType() throws AppException {
        ServerConfig.Builder builder = ServerConfig.newBuilder();
        if (this.timeout != null) {
            builder.setTimeout(this.timeout);
        }
        if (this.allowTransparent != null) {
            builder.setAllowTransparent(this.allowTransparent);
        }
        if (this.userLevel != null) {
            builder.setUserLevel(this.userLevel);
        }
        if (!CollectionUtils.isEmpty(this.accounts)) {
            for (Account account : this.accounts) {
                builder.putAccounts(account.getUser(), account.getPass());
            }
        }
        return builder.build();
    }
}
