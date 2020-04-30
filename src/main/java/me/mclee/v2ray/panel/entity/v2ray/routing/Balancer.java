package me.mclee.v2ray.panel.entity.v2ray.routing;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Balancer {
    private String tag;
    private List<String> selector;
}
