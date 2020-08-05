package me.mclee.v2ray.panel.grpc;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.v2ray.core.app.log.command.LoggerServiceGrpc;
import com.v2ray.core.app.log.command.RestartLoggerRequest;
import com.v2ray.core.app.log.command.RestartLoggerResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class LoggerGrpcClient implements Closeable {
    private final ManagedChannel channel;
    private final LoggerServiceGrpc.LoggerServiceBlockingStub loggerServiceBlockingStub;

    public LoggerGrpcClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        loggerServiceBlockingStub = LoggerServiceGrpc.newBlockingStub(channel);
    }

    /**
     * 重启 V2RAY 日志服务
     */
    public void restartLogger() {
        RestartLoggerResponse ignored = loggerServiceBlockingStub.restartLogger(RestartLoggerRequest.newBuilder().build());
    }

    @Override
    public void close() throws IOException {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
    }
}
