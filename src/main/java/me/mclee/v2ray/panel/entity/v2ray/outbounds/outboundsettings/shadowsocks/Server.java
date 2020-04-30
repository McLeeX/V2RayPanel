package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.shadowsocks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.entity.v2ray.Method;

@Setter
@Getter
@AllArgsConstructor
public class Server {
    private String email;
    private String address;
    private Integer port;
    private Method method;
    private String password;
    private Integer level;
    private Boolean ota;
}
