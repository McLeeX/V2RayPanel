package me.mclee.v2ray.panel.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.utils.JsonUtils;
import me.mclee.v2ray.panel.entity.v2ray.Config;
import me.mclee.v2ray.panel.grpc.GrpcClientFactory;
import me.mclee.v2ray.panel.grpc.LoggerGrpcClient;
import me.mclee.v2ray.panel.log.V2rayLoggerProcess;
import me.mclee.v2ray.panel.service.V2rayService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * V2ray 工具类
 */
@Slf4j
@Component
public class V2rayServiceImpl implements V2rayService, InitializingBean, DisposableBean {

    private static final String V2RAY_PATH = "D:\\V2RayW\\v2ray-core\\v2ray.exe";

    private static final String V2RAY_CONFIG_PATH = "D:\\V2RayW\\config\\config.json";

    @Autowired
    private GrpcClientFactory grpcClientFactory;

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
            Thread thread = new Thread(new V2rayLoggerProcess(v2rayProcess));
            thread.setName("v2ray-logger");
            thread.setDaemon(true);
            thread.start();
            log.info("成功启动 v2ray 服务。");
        } catch (IOException e) {
            log.error("v2ray 服务启动失败。", e);
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
            log.info("停止 v2ray 服务。");
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
            log.error("读取配置文件信息失败。", e);
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
            log.error("更新配置文件信息失败。", e);
            throw new AppException(ErrorCode.INTERNAL_ERROR, e);
        }
    }

    /**
     * 重启日志服务
     *
     * @throws AppException 重启失败
     */
    @Override
    public void restartLogger() throws AppException {
        try (LoggerGrpcClient client = grpcClientFactory.getLoggerGrpcClient()) {
            client.restartLogger();
        } catch (IOException e) {
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
