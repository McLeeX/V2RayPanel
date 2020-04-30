package me.mclee.v2ray.panel.entity.v2ray.streamsettings.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class HTTPResponse {
    private String version;
    private int status;
    private String reason;
    private Map<String, List<String>> headers;
}
