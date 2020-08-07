package me.mclee.v2ray.panel.common.utils;

import java.util.UUID;

import com.google.protobuf.GeneratedMessageV3;
import com.v2ray.core.common.serial.TypedMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtils {

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

    /**
     * 随机生成 uuid
     *
     * @return uuid
     */
    public static UUID generateUuid() {
        return UUID.randomUUID();
    }
}
