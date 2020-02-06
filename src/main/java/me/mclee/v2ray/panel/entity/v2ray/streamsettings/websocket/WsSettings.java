package me.mclee.v2ray.panel.entity.v2ray.streamsettings.websocket;

import java.util.Map;

public class WsSettings {
    private String path;
    private Map<String, String> headers;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
