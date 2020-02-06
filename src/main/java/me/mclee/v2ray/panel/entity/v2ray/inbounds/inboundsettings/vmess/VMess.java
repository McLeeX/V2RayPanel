package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.vmess;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;

import java.util.List;

public class VMess extends InboundSettings {
    private List<Client> clients;
    @JsonProperty("default")
    private Default defaultConfig;
    private Detour detour;
    private Boolean disableInsecureEncryption;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Default getDefaultConfig() {
        return defaultConfig;
    }

    public void setDefaultConfig(Default defaultConfig) {
        this.defaultConfig = defaultConfig;
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
