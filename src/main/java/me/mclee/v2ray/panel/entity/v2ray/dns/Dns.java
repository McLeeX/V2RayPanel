package me.mclee.v2ray.panel.entity.v2ray.dns;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Dns {
    private Map<String, String> hosts;
    private List<Server> servers;
    private String clientIp;
    private String tag;
}
