package me.mclee.v2ray.panel.entity.v2ray.reverse;

import java.util.List;

public class Reverse {
    private List<Bridge> bridges;
    private List<Portal> portals;

    public List<Bridge> getBridges() {
        return bridges;
    }

    public void setBridges(List<Bridge> bridges) {
        this.bridges = bridges;
    }

    public List<Portal> getPortals() {
        return portals;
    }

    public void setPortals(List<Portal> portals) {
        this.portals = portals;
    }
}
