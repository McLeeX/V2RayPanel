package me.mclee.v2ray.panel;

import me.mclee.v2ray.panel.grpc.LoggerService;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        try (LoggerService loggerService = new LoggerService("127.0.0.1", 10053)) {
            loggerService.restartLogger();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
