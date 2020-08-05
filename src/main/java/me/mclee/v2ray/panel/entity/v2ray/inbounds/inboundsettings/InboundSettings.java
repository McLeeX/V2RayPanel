package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings;

import java.io.Serializable;

import com.google.protobuf.GeneratedMessageV3;
import me.mclee.v2ray.panel.common.AppException;

public abstract class InboundSettings implements Serializable {
    abstract public GeneratedMessageV3 toGrpcType() throws AppException;
}
