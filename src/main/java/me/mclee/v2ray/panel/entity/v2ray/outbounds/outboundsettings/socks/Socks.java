package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.socks;

import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;

import java.util.List;

public class Socks extends OutboundSettings {
    private List<Server> servers;

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
}
