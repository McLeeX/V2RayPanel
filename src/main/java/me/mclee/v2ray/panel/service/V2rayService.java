package me.mclee.v2ray.panel.service;

import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.entity.v2ray.Config;

public interface V2rayService {
    /**
     * 启动 V2ray
     */
    void start() throws AppException;

    /**
     * 停止 V2ray
     */
    void stop();

    /**
     * 读取当前配置
     *
     * @return v2ray 配置类
     */
    Config getConfig() throws AppException;

    /**
     * 更新配置文件
     *
     * @param config 配置信息
     * @throws AppException 更新文件失败
     */
    void updateConfig(Config config) throws AppException;
}
