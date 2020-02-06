package me.mclee.v2ray.panel.entity.v2ray.outbounds.mux;

public class Mux {
    private Boolean enabled;
    private Integer concurrency;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(Integer concurrency) {
        this.concurrency = concurrency;
    }
}
