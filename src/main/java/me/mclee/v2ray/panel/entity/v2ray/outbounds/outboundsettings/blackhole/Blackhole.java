package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.blackhole;

import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;

public class Blackhole extends OutboundSettings {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
