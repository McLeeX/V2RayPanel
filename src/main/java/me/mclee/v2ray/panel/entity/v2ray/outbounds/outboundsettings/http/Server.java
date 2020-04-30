package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.entity.v2ray.Account;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Server {
    private String address;
    private Integer port;
    private List<Account> users;
}
