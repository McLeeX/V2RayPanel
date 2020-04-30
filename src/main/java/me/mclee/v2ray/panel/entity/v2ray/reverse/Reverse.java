package me.mclee.v2ray.panel.entity.v2ray.reverse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Reverse {
    private List<Bridge> bridges;
    private List<Portal> portals;
}
