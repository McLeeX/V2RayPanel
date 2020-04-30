package me.mclee.v2ray.panel.entity.v2ray.streamsettings.http;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HttpSettings {
    private List<String> host;
    private String path;
}
