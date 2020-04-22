package me.mclee.v2ray.panel.service;

import me.mclee.v2ray.panel.common.AppException;

public interface V2rayService {
    /**
     * 启动 V2ray
     */
    void start() throws AppException;

    /**
     * 停止 V2ray
     */
    void stop();
}
