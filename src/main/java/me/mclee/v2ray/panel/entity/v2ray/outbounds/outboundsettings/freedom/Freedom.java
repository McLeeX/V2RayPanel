package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.freedom;

import com.v2ray.core.proxy.freedom.Config;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;

@Setter
@Getter
public class Freedom extends OutboundSettings {
    private DomainStrategy domainStrategy;
    private String redirect;
    private Integer userLevel;

    @Override
    public Config toGrpcType() throws AppException {
        Config.Builder builder = Config.newBuilder();
        Config.DomainStrategy ds;
        switch (domainStrategy) {
            case UseIP:
                ds = Config.DomainStrategy.USE_IP;
                break;
            case UseIPv4:
                ds = Config.DomainStrategy.USE_IP4;
                break;
            case UseIPv6:
                ds = Config.DomainStrategy.USE_IP6;
                break;
            case AsIs:
            default:
                ds = Config.DomainStrategy.AS_IS;
        }
        builder.setDomainStrategy(ds);
        if (userLevel != null) {
            builder.setUserLevel(userLevel);
        }
        // TODO redirect
        return builder.build();
    }
}
