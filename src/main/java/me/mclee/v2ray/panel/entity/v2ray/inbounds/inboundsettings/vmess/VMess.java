package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.vmess;

import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;

import java.util.List;

public class VMess extends InboundSettings {
    private List<Client> clients;
    private Default aDefault;
    private Detour detour;
    private Boolean disableInsecureEncryption;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Default getaDefault() {
        return aDefault;
    }

    public void setaDefault(Default aDefault) {
        this.aDefault = aDefault;
    }

    public Detour getDetour() {
        return detour;
    }

    public void setDetour(Detour detour) {
        this.detour = detour;
    }

    public Boolean getDisableInsecureEncryption() {
        return disableInsecureEncryption;
    }

    public void setDisableInsecureEncryption(Boolean disableInsecureEncryption) {
        this.disableInsecureEncryption = disableInsecureEncryption;
    }
}
