package me.mclee.v2ray.panel.entity.v2ray.api;

import java.util.List;

/**
 * config.api
 */
public class Api {
    private String tag;
    private List<Service> services;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
