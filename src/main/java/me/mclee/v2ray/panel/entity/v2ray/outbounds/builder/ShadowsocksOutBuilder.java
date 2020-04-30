package me.mclee.v2ray.panel.entity.v2ray.outbounds.builder;

import lombok.NoArgsConstructor;
import me.mclee.v2ray.panel.entity.v2ray.Method;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.shadowsocks.Server;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.shadowsocks.Shadowsocks;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ShadowsocksOutBuilder {

    private List<Server> servers = new ArrayList<>();

    public Shadowsocks build() {
        Shadowsocks shadowsocks = new Shadowsocks();
        shadowsocks.setServers(servers);
        return shadowsocks;
    }

    public ShadowsocksOutBuilder addServer(String email, String address, Integer port, Method method, String password, Integer level, Boolean ota) {
        Server server = new Server(email, address, port, method, password, level, ota);
        this.servers.add(server);
        return this;
    }

    public ShadowsocksOutBuilder setAllServer(List<Server> servers) {
        this.servers = servers;
        return this;
    }
}
