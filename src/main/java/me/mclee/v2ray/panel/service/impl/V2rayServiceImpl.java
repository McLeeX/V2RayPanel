package me.mclee.v2ray.panel.service.impl;

import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.utils.JsonUtils;
import me.mclee.v2ray.panel.entity.v2ray.Config;
import me.mclee.v2ray.panel.service.V2rayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
                try (InputStream inputStream = v2rayProcess.getInputStream();
                     InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                     BufferedReader reader = new BufferedReader(inputStreamReader)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        LOGGER.info(line);
                    }
                } catch (IOException e) {
                    LOGGER.error("v2ray 日志收集失败。", e);
                }
            });
            thread.setName("v2ray-logger");
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

    /**
     * 读取当前配置
     *
     * @return v2ray 配置类
     */
    @Override
    public Config getConfig() throws AppException {
        Path path = Paths.get(V2RAY_CONFIG_PATH);
        try {
            List<String> lines = Files.readAllLines(path);
            StringBuilder stringBuilder = new StringBuilder();
            lines.forEach(stringBuilder::append);
            return JsonUtils.string2Obj(stringBuilder.toString(), Config.class);
        } catch (IOException e) {
            LOGGER.error("读取配置文件信息失败。", e);
            throw new AppException(ErrorCode.INTERNAL_ERROR, e);
        }
    }

    /**
     * 更新配置文件
     *
     * @param config 配置信息
     * @throws AppException 更新文件失败
     */
    @Override
    public synchronized void updateConfig(Config config) throws AppException {
        String json = JsonUtils.obj2StringPretty(config);
        Path path = Paths.get(V2RAY_CONFIG_PATH);
        try {
            Files.write(path, json.getBytes());
        } catch (IOException e) {
            LOGGER.error("更新配置文件信息失败。", e);
            throw new AppException(ErrorCode.INTERNAL_ERROR, e);
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
