package me.mclee.v2ray.panel.entity.v2ray.outbounds.mux;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Mux {
    private Boolean enabled;
    private Integer concurrency;
}
