package me.mclee.v2ray.panel.entity.v2ray.inbounds.sniffing;

import java.util.Set;

public class Sniffing {
    private Boolean enabled;
    private Set<DestOverride> destOverride;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<DestOverride> getDestOverride() {
        return destOverride;
    }

    public void setDestOverride(Set<DestOverride> destOverride) {
        this.destOverride = destOverride;
    }
}
