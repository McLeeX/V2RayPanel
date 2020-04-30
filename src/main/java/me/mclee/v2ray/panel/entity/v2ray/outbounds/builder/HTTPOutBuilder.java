package me.mclee.v2ray.panel.entity.v2ray.outbounds.builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.http.HTTP;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.http.Server;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class HTTPOutBuilder {
    private List<Server> servers = new ArrayList<>();

    public HTTP build() {
        HTTP http = new HTTP();
        http.setServers(servers);
        return http;
    }

    public HTTPOutBuilder addServer(@NotNull Server server) {
        servers.add(server);
        return this;
    }

    public HTTPOutBuilder setAllServers(@NotNull List<Server> servers) {
        this.servers = servers;
        return this;
    }
}
