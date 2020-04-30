package me.mclee.v2ray.panel.entity.v2ray.inbounds.sniffing;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class Sniffing {
    private Boolean enabled;
    private Set<DestOverride> destOverride;
}
