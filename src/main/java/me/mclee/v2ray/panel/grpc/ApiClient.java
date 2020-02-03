package me.mclee.v2ray.panel.grpc;

import com.google.protobuf.GeneratedMessageV3;
import com.v2ray.core.app.log.command.LoggerServiceGrpc;
import com.v2ray.core.app.proxyman.command.HandlerServiceGrpc;
import com.v2ray.core.app.stats.command.StatsServiceGrpc;
import com.v2ray.core.common.serial.TypedMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApiClient implements Closeable {
    private final ManagedChannel channel;
    private final HandlerServiceGrpc.HandlerServiceBlockingStub handlerServiceBlockingStub;
    private final LoggerServiceGrpc.LoggerServiceBlockingStub loggerServiceBlockingStub;
    private final StatsServiceGrpc.StatsServiceBlockingStub statsServiceBlockingStub;

    public ApiClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).build();
        handlerServiceBlockingStub = HandlerServiceGrpc.newBlockingStub(channel);
        loggerServiceBlockingStub = LoggerServiceGrpc.newBlockingStub(channel);
        statsServiceBlockingStub = StatsServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void close() throws IOException {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
    }

    /**
     * 转化需要的结构体为TypedMessage
     *
     * @param message V2RayApi proto 结构体
     * @return TypedMessage
     */
    private TypedMessage convertToTypedMessage(GeneratedMessageV3 message) {
        return TypedMessage.newBuilder()
                .setType(message.getDescriptorForType().getFullName())
                .setValue(message.toByteString()).build();
    }
}
