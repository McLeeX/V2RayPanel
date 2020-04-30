package me.mclee.v2ray.panel.entity.v2ray;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.entity.v2ray.api.Api;
import me.mclee.v2ray.panel.entity.v2ray.dns.Dns;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.Inbound;
import me.mclee.v2ray.panel.entity.v2ray.log.Log;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.Outbound;
import me.mclee.v2ray.panel.entity.v2ray.policy.Policy;
import me.mclee.v2ray.panel.entity.v2ray.routing.Routing;
import me.mclee.v2ray.panel.entity.v2ray.stats.Stats;
import me.mclee.v2ray.panel.entity.v2ray.transport.Transport;

import java.util.List;

@Setter
@Getter
public class Config {
    private Log log;
    private Api api;
    private Dns dns;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Stats stats;
    private Routing routing;
    private Policy policy;
    private List<Inbound> inbounds;
    private List<Outbound> outbounds;
    private Transport transport;
}
