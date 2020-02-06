package me.mclee.v2ray.panel.entity.v2ray.streamsettings.http;

import java.util.List;

public class HttpSettings {
    private List<String> host;
    private String path;

    public List<String> getHost() {
        return host;
    }

    public void setHost(List<String> host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
