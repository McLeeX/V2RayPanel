package me.mclee.v2ray.panel.entity.v2ray.streamsettings.common;

import java.util.List;
import java.util.Map;

public class HTTPResponse {
    private String version;
    private int status;
    private String reason;
    private Map<String, List<String>> headers;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }
}
