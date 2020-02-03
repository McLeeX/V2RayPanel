package me.mclee.v2ray.panel.entity.v2ray.dns;

import java.util.List;
import java.util.Map;

public class Dns {
    private Map<String, String> hosts;
    private List<Service> services;

    public Map<String, String> getHosts() {
        return hosts;
    }

    public void setHosts(Map<String, String> hosts) {
        this.hosts = hosts;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
