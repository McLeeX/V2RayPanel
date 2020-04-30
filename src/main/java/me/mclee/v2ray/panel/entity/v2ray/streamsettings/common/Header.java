package me.mclee.v2ray.panel.entity.v2ray.streamsettings.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Header {
    private Type type;
    private HTTPRequest request;
    private HTTPResponse response;
}
