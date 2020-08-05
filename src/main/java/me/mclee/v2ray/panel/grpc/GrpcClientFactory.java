package me.mclee.v2ray.panel.grpc;

import org.springframework.stereotype.Component;

@Component
public class GrpcClientFactory {

    private static final String V2RAY_IP = "127.0.0.1";

    private static final int V2RAY_PORT = 9000;

    public HandlerGrpcClient getHandlerGrpcClient() {
        return new HandlerGrpcClient(V2RAY_IP, V2RAY_PORT);
    }

    public LoggerGrpcClient getLoggerGrpcClient() {
        return new LoggerGrpcClient(V2RAY_IP, V2RAY_PORT);
    }

    public StatsGrpcClient getStatsGrpcClient() {
        return new StatsGrpcClient(V2RAY_IP, V2RAY_PORT);
    }
}
