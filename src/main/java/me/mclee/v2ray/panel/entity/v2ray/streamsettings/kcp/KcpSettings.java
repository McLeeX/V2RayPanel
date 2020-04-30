package me.mclee.v2ray.panel.entity.v2ray.streamsettings.kcp;

import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.common.Header;

@Setter
@Getter
public class KcpSettings {
    private Integer mtu;
    private Integer tti;
    private Integer uplinkCapacity;
    private Integer downlinkCapacity;
    private Boolean congestion;
    private Integer readBufferSize;
    private Integer writeBufferSize;
    private Header header;
}
