package me.mclee.v2ray.panel.entity.v2ray.streamsettings.common;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class HTTPRequest {
    private String version;
    private HttpMethod method;
    private List<String> path;
    private Map<String, List<String>> headers;
}
