package me.mclee.v2ray.panel.entity.v2ray.policy;

public class SystemPolicy {
    private Boolean statsInboundUplink;
    private Boolean statsInboundDownlink;

    public Boolean getStatsInboundUplink() {
        return statsInboundUplink;
    }

    public void setStatsInboundUplink(Boolean statsInboundUplink) {
        this.statsInboundUplink = statsInboundUplink;
    }

    public Boolean getStatsInboundDownlink() {
        return statsInboundDownlink;
    }

    public void setStatsInboundDownlink(Boolean statsInboundDownlink) {
        this.statsInboundDownlink = statsInboundDownlink;
    }
}
