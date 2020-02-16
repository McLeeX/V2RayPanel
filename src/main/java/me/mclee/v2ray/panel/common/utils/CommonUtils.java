package me.mclee.v2ray.panel.common.utils;

import com.google.protobuf.GeneratedMessageV3;
import com.v2ray.core.common.serial.TypedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 转化需要的结构体为TypedMessage
     *
     * @param message V2RayApi proto 结构体
     * @return TypedMessage
     */
    public static TypedMessage convertToTypedMessage(GeneratedMessageV3 message) {
        return TypedMessage.newBuilder()
                           .setType(message.getDescriptorForType().getFullName())
                           .setValue(message.toByteString()).build();
    }

}
