package me.mclee.v2ray.panel.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class V2rayLoggerProcess implements Runnable {

    @Getter
    private final Process v2rayProcess;

    public V2rayLoggerProcess(Process v2rayProcess) {
        this.v2rayProcess = v2rayProcess;
    }

    @Override
    public void run() {
        try (InputStream inputStream = v2rayProcess.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                log.info(line);
            }
            if (!v2rayProcess.isAlive()) {
                log.warn("v2ray 进程退出。");
            }
        } catch (IOException e) {
            log.error("v2ray 日志收集失败。", e);
        }
    }
}
