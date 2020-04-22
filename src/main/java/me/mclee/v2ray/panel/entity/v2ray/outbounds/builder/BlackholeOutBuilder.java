package me.mclee.v2ray.panel.entity.v2ray.outbounds.builder;

import me.mclee.v2ray.panel.entity.v2ray.outbounds.Outbound;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.blackhole.Blackhole;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.blackhole.Response;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.blackhole.Type;

public class BlackholeOutBuilder {

    private String tag;
    private Type type;

    public BlackholeOutBuilder(String tag) {
        this.tag = tag;
    }

    public Outbound build() {
        Outbound outbound = new Outbound();
        outbound.setTag(tag);
        Blackhole blackhole = new Blackhole();
        Response response = new Response();
        response.setType(type);
        outbound.setSettings(blackhole);
        return outbound;
    }

    public String getTag() {
        return tag;
    }

    public BlackholeOutBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Type getType() {
        return type;
    }

    public BlackholeOutBuilder setType(Type type) {
        this.type = type;
        return this;
    }
}
