package me.mclee.v2ray.panel.entity.v2ray.inbounds.allocate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Allocate {
    private Strategy strategy;
    private Integer refresh;
    private Integer concurrency;
}
