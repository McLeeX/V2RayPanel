package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings;

import com.google.protobuf.GeneratedMessageV3;
import me.mclee.v2ray.panel.common.AppException;

import java.io.Serializable;

public abstract class InboundSettings implements Serializable {
    abstract public GeneratedMessageV3 toGRpcType() throws AppException;
}
