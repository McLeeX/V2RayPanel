package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.blackhole;

import com.v2ray.core.common.serial.TypedMessage;
import com.v2ray.core.proxy.blackhole.Config;
import com.v2ray.core.proxy.blackhole.HTTPResponse;
import com.v2ray.core.proxy.blackhole.NoneResponse;
import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.common.utils.CommonUtils;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.OutboundSettings;

import java.util.Optional;

@Setter
@Getter
public class Blackhole extends OutboundSettings {
    private Response response;

    @Override
    public Config toGRpcType() {
        Type type = Optional.ofNullable(response).map(Response::getType).orElse(Type.none);
        TypedMessage response;
        switch (type) {
            case http:
                response = CommonUtils.convertToTypedMessage(HTTPResponse.newBuilder().build());
                break;
            case none:
            default:
                response = CommonUtils.convertToTypedMessage(NoneResponse.newBuilder().build());
        }
        return Config.newBuilder().setResponse(response).build();
    }
}
