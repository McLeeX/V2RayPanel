package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.vmess;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Vnext {
    private String address;
    private Integer port;
    private List<User> users;
}
