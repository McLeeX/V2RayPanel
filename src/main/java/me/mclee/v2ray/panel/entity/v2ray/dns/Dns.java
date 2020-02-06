package me.mclee.v2ray.panel.entity.v2ray.dns;

import java.util.List;
import java.util.Map;

public class Dns {
    private Map<String, String> hosts;
    private List<Server> servers;
    private String clientIp;
    private String tag;

    public Map<String, String> getHosts() {
        return hosts;
    }

    public void setHosts(Map<String, String> hosts) {
        this.hosts = hosts;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
