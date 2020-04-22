package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings;

import com.google.protobuf.GeneratedMessageV3;
import me.mclee.v2ray.panel.common.AppException;

import java.io.Serializable;

public abstract class OutboundSettings implements Serializable {
    abstract public GeneratedMessageV3 toGRpcType() throws AppException;
}
