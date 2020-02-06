package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.vmess;

import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;

import java.util.List;

public class VMess extends OutboundSettings {
    private List<Vnext> vnext;

    public List<Vnext> getVnext() {
        return vnext;
    }

    public void setVnext(List<Vnext> vnext) {
        this.vnext = vnext;
    }
}
