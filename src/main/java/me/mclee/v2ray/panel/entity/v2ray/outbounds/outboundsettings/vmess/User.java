package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.vmess;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class User {
    private UUID id;
    private Integer alterId;
    private Security security;
    private Integer level;
}
