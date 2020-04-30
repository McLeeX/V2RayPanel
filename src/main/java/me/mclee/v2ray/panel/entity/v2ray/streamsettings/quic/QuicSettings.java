package me.mclee.v2ray.panel.entity.v2ray.streamsettings.quic;


import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.common.Header;

@Setter
@Getter
public class QuicSettings {
    private Security security;
    private String key;
    private Header header;
}
