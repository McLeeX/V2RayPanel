package me.mclee.v2ray.panel.grpc;

import com.google.protobuf.GeneratedMessageV3;
import com.v2ray.core.InboundHandlerConfig;
import com.v2ray.core.app.proxyman.command.AddInboundRequest;
import com.v2ray.core.app.proxyman.command.AddInboundResponse;
import com.v2ray.core.app.proxyman.command.HandlerServiceGrpc;
import com.v2ray.core.common.serial.TypedMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.Inbound;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HandlerService implements Closeable {
    private final ManagedChannel channel;
    private final HandlerServiceGrpc.HandlerServiceBlockingStub handlerServiceBlockingStub;

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

    public HandlerService(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).build();
        handlerServiceBlockingStub = HandlerServiceGrpc.newBlockingStub(channel);
    }

    public void addInbound(Inbound inbound) {
        String tag = inbound.getTag();
        TypedMessage proxySettings = convertToTypedMessage(null);
        TypedMessage receiverSettings = convertToTypedMessage(null);
        InboundHandlerConfig inboundConfig = InboundHandlerConfig.newBuilder().setTag(tag)
                .setProxySettings(proxySettings).setReceiverSettings(receiverSettings).build();
        AddInboundResponse ignored = handlerServiceBlockingStub.addInbound(AddInboundRequest.newBuilder()
                .setInbound(inboundConfig).build());
    }

    public void removeInbound(String tag) {

    }

    public void alterInbound(String tag, Inbound inbound) {

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
