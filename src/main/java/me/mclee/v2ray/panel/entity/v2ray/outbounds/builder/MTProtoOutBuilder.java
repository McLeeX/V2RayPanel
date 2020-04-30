package me.mclee.v2ray.panel.entity.v2ray.outbounds.builder;

import lombok.NoArgsConstructor;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.mtproto.MTProto;

@NoArgsConstructor
public class MTProtoOutBuilder {

    public MTProto build() {
        return new MTProto();
    }
}
