package me.mclee.v2ray.panel.entity.v2ray.outbounds.builder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.Outbound;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.blackhole.Blackhole;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.blackhole.Response;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.blackhole.Type;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class BlackholeOutBuilder {

    @NotNull
    private String tag;
    private Type type;

    public Outbound build() {
        Outbound outbound = new Outbound();
        outbound.setTag(tag);
        Blackhole blackhole = new Blackhole();
        Response response = new Response();
        response.setType(type);
        outbound.setSettings(blackhole);
        return outbound;
    }

    public BlackholeOutBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public BlackholeOutBuilder setType(Type type) {
        this.type = type;
        return this;
    }
}
