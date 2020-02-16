package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.shadowsocks;

import com.v2ray.core.common.protocol.User;
import com.v2ray.core.proxy.shadowsocks.Account;
import com.v2ray.core.proxy.shadowsocks.CipherType;
import com.v2ray.core.proxy.shadowsocks.ServerConfig;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.utils.CommonUtils;
import me.mclee.v2ray.panel.entity.v2ray.Method;
import me.mclee.v2ray.panel.entity.v2ray.Network;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;

public class Shadowsocks extends InboundSettings {
    private String email;
    private Method method;
    private String password;
    private Integer level;
    private Boolean ota;
    private Network network;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getOta() {
        return ota;
    }

    public void setOta(Boolean ota) {
        this.ota = ota;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    @Override
    public ServerConfig toGRpcType() throws AppException {
        ServerConfig.Builder builder = ServerConfig.newBuilder();
        if (this.network != null) {
            builder.addAllNetwork(this.network.toGRpcSet());
        }
        Account.Builder accountBuilder = Account.newBuilder().setPassword(this.password);
        if (this.method != null) {
            CipherType cipherType;
            switch (this.method) {
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
                    cipherType = CipherType.CHACHA20_POLY1305;
                    break;
                default:
                    cipherType = null;
            }
            accountBuilder.setCipherType(cipherType);
        }
        if (this.ota != null) {
            if (this.ota) {
                accountBuilder.setOta(Account.OneTimeAuth.Enabled);
            } else {
                accountBuilder.setOta(Account.OneTimeAuth.Disabled);
            }
        }
        Account account = accountBuilder.build();
        User user = User.newBuilder().setLevel(this.level).setEmail(this.email)
                        .setAccount(CommonUtils.convertToTypedMessage(account)).build();
        builder.setUser(user);
        return builder.build();
    }
}
