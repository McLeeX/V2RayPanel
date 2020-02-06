package me.mclee.v2ray.panel.entity.v2ray.streamsettings.common;


import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;

public class HTTPRequest {
    private String version;
    private HttpMethod method;
    private List<String> path;
    private Map<String, List<String>> headers;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }
}
