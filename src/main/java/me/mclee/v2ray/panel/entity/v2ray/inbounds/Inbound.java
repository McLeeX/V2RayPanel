package me.mclee.v2ray.panel.entity.v2ray.inbounds;

import me.mclee.v2ray.panel.entity.v2ray.inbounds.streamsettings.StreamSettings;

public class Inbound {
    private String port;
    private String listen;
    private Protocol protocol;
    private InboundSetting settings;
    private StreamSettings streamSettings;
    private String tag;
    private Sniffing sniffing;
    private Allocate allocate;
}
