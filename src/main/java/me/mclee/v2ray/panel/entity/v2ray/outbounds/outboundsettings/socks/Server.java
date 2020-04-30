package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.socks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Server {
    private String address;
    private Integer port;
    private List<User> users;
}
