package me.mclee.v2ray.panel.entity.v2ray.outbounds.builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.socks.Server;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.socks.Socks;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SocksOutBuilder {

    private List<Server> servers = new ArrayList<>();

    public Socks build() {
        Socks socks = new Socks();
        socks.setServers(servers);
        return socks;
    }

    public SocksOutBuilder addServer(Server server) {
        this.servers.add(server);
        return this;
    }

    public SocksOutBuilder setAllServers(List<Server> servers) {
        this.servers = servers;
        return this;
    }
}
