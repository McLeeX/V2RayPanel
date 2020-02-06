package me.mclee.v2ray.panel.entity.v2ray;

import com.fasterxml.jackson.annotation.JsonInclude;
import me.mclee.v2ray.panel.entity.v2ray.api.Api;
import me.mclee.v2ray.panel.entity.v2ray.dns.Dns;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.Inbound;
import me.mclee.v2ray.panel.entity.v2ray.log.Log;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.Outbound;
import me.mclee.v2ray.panel.entity.v2ray.policy.Policy;
import me.mclee.v2ray.panel.entity.v2ray.routing.Routing;
import me.mclee.v2ray.panel.entity.v2ray.stats.Stats;
import me.mclee.v2ray.panel.entity.v2ray.transport.Transport;

public class Config {
    private Log log;
    private Api api;
    private Dns dns;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Stats stats;
    private Routing routing;
    private Policy policy;
    private Inbound inbounds;
    private Outbound outbound;
    private Transport transport;

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public Dns getDns() {
        return dns;
    }

    public void setDns(Dns dns) {
        this.dns = dns;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Routing getRouting() {
        return routing;
    }

    public void setRouting(Routing routing) {
        this.routing = routing;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public Inbound getInbounds() {
        return inbounds;
    }

    public void setInbounds(Inbound inbounds) {
        this.inbounds = inbounds;
    }

    public Outbound getOutbound() {
        return outbound;
    }

    public void setOutbound(Outbound outbound) {
        this.outbound = outbound;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}
