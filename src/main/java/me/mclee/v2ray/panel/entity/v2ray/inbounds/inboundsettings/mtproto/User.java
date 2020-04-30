package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.mtproto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String emails;
    private Integer level;
    private String secret;
}
