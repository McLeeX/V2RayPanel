package me.mclee.v2ray.panel.grpc;

import com.v2ray.core.app.log.command.LoggerServiceGrpc;
import com.v2ray.core.app.log.command.RestartLoggerRequest;
import com.v2ray.core.app.log.command.RestartLoggerResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoggerService implements Closeable {
    private final ManagedChannel channel;
    private final LoggerServiceGrpc.LoggerServiceBlockingStub loggerServiceBlockingStub;

    public LoggerService(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        loggerServiceBlockingStub = LoggerServiceGrpc.newBlockingStub(channel);
    }

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
