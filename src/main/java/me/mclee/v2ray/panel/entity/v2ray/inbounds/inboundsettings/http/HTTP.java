package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.http;

import com.v2ray.core.proxy.http.ServerConfig;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.entity.v2ray.Account;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class HTTP extends InboundSettings {
    private Integer timeout;
    private List<Account> accounts;
    private Boolean allowTransparent;
    private Integer userLevel;

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Boolean getAllowTransparent() {
        return allowTransparent;
    }

    public void setAllowTransparent(Boolean allowTransparent) {
        this.allowTransparent = allowTransparent;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public ServerConfig toGRpcType() throws AppException {
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
