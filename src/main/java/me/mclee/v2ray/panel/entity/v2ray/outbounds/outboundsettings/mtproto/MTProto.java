package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.mtproto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.v2ray.core.proxy.mtproto.ClientConfig;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MTProto extends OutboundSettings {
    @Override
    public ClientConfig toGrpcType() {
        return ClientConfig.newBuilder().build();
    }
}
