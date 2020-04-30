package me.mclee.v2ray.panel.entity.v2ray.streamsettings;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Sockopt {
    private Integer mark;
    private Boolean tcpFastOpen;
    private String tproxy;
}
