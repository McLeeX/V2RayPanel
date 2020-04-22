package me.mclee.v2ray.panel.service.impl;

import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.service.V2rayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * V2ray 工具类
 */
@Component
public class V2rayServiceImpl implements V2rayService, InitializingBean, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(V2rayServiceImpl.class);

    private static final String V2RAY_PATH = "C:\\V2RayW\\v2ray-core\\v2ray.exe";

    private static final String V2RAY_CONFIG_PATH = "C:\\V2RayW\\v2ray-core\\config.json";

    private Process v2rayProcess = null;

    /**
     * 启动 V2ray
     */
    @Override
    public void start() throws AppException {
        this.stop();
        ProcessBuilder pb = new ProcessBuilder(V2RAY_PATH, "-c", V2RAY_CONFIG_PATH);
        pb.redirectErrorStream(true);
        try {
            v2rayProcess = pb.start();
            Thread thread = new Thread(() -> {
                try (InputStream inputStream = v2rayProcess.getInputStream()) {
                    byte[] buffer = new byte[2048];
                    while (inputStream.read(buffer) > 0) {
                        System.out.print(new String(buffer));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();
            LOGGER.info("成功启动 v2ray 服务。");
        } catch (IOException e) {
            LOGGER.error("v2ray 服务启动失败。", e);
            throw new AppException(ErrorCode.INTERNAL_ERROR, e);
        }
    }

    /**
     * 停止 V2ray
     */
    @Override
    public void stop() {
        if (v2rayProcess != null) {
            v2rayProcess.destroy();
            LOGGER.info("停止 v2ray 服务。");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.start();
    }

    @Override
    public void destroy() {
        this.stop();
    }
}
