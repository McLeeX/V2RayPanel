package me.mclee.v2ray.panel.entity.v2ray.streamsettings.websocket;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class WsSettings {
    private String path;
    private Map<String, String> headers;
}
