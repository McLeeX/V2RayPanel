package me.mclee.v2ray.panel.entity.v2ray.outbounds.builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.dns.Dns;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.dns.Network;

@Getter
@NoArgsConstructor
public class DnsOutBuilder {
    private Network network;
    private String address;
    private Integer port;

    public Dns build() {
        Dns dns = new Dns();
        dns.setNetwork(network);
        dns.setAddress(address);
        dns.setPort(port);
        return dns;
    }

    public DnsOutBuilder setNetwork(Network network) {
        this.network = network;
        return this;
    }

    public DnsOutBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public DnsOutBuilder setPort(Integer port) {
        this.port = port;
        return this;
    }
}
