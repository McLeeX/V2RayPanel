package me.mclee.v2ray.panel.entity.v2ray.routing;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Routing {
    private DomainStrategy domainStrategy;
    private List<Rule> rules;
    private List<Balancer> balancers;
}
