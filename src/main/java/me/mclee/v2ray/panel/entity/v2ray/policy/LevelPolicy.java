package me.mclee.v2ray.panel.entity.v2ray.policy;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LevelPolicy {
    private int handshake = 4;
    private int connIdle = 300;
    private int uplinkOnly = 2;
    private int downlinkOnly = 5;
    private Boolean statusUserUplink;
    private Boolean statusUserDownlink;
    private long bufferSize;
}
