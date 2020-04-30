package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.vmess;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Client {
    private UUID id;
    private Integer level;
    private Integer alterId;
    private String email;
}
