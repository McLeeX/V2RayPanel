package me.mclee.v2ray.panel.grpc;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.google.protobuf.GeneratedMessageV3;
import com.v2ray.core.InboundHandlerConfig;
import com.v2ray.core.OutboundHandlerConfig;
import com.v2ray.core.app.proxyman.command.AddInboundRequest;
import com.v2ray.core.app.proxyman.command.AddInboundResponse;
import com.v2ray.core.app.proxyman.command.AddOutboundRequest;
import com.v2ray.core.app.proxyman.command.AddOutboundResponse;
import com.v2ray.core.app.proxyman.command.AddUserOperation;
import com.v2ray.core.app.proxyman.command.AlterInboundRequest;
import com.v2ray.core.app.proxyman.command.AlterInboundResponse;
import com.v2ray.core.app.proxyman.command.HandlerServiceGrpc;
import com.v2ray.core.app.proxyman.command.RemoveInboundRequest;
import com.v2ray.core.app.proxyman.command.RemoveInboundResponse;
import com.v2ray.core.app.proxyman.command.RemoveOutboundRequest;
import com.v2ray.core.app.proxyman.command.RemoveOutboundResponse;
import com.v2ray.core.app.proxyman.command.RemoveUserOperation;
import com.v2ray.core.common.protocol.User;
import com.v2ray.core.common.serial.TypedMessage;
import com.v2ray.core.proxy.vmess.Account;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.Inbound;
import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.vmess.Client;
import me.mclee.v2ray.panel.entity.v2ray.outbounds.Outbound;
import org.jetbrains.annotations.NotNull;

public class HandlerGrpcClient implements Closeable {
    private final ManagedChannel channel;
    private final HandlerServiceGrpc.HandlerServiceBlockingStub handlerServiceBlockingStub;

    public HandlerGrpcClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).build();
        handlerServiceBlockingStub = HandlerServiceGrpc.newBlockingStub(channel);
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

    /**
     * 添加一个 Inbound 配置
     *
     * @param inbound 配置信息
     */
    public void addInbound(@NotNull Inbound inbound) throws AppException {
        InboundHandlerConfig inboundConfig = inbound.toInboundHandlerConfig();
        AddInboundResponse ignored = handlerServiceBlockingStub.addInbound(AddInboundRequest.newBuilder()
                .setInbound(inboundConfig)
                .build());
    }

    /**
     * 删除一个 Inbound 配置
     *
     * @param tag 要删除的 Inbound 的标签名
     */
    public void removeInbound(String tag) {
        RemoveInboundRequest request = RemoveInboundRequest.newBuilder().setTag(tag).build();
        RemoveInboundResponse ignored = handlerServiceBlockingStub.removeInbound(request);
    }

    /**
     * 为 Vmess Inbound 添加用户
     *
     * @param tag              标签名
     * @param vmessInboundUser 用户信息
     */
    public void addVmessInboundUser(String tag, @NotNull Client vmessInboundUser) {
        Account account = Account.newBuilder().setId(vmessInboundUser.getId().toString())
                .setAlterId(vmessInboundUser.getAlterId()).build();
        User user = User.newBuilder().setLevel(vmessInboundUser.getLevel())
                .setEmail(vmessInboundUser.getEmail()).setAccount(convertToTypedMessage(account)).build();
        AddUserOperation operation = AddUserOperation.newBuilder().setUser(user).build();
        AlterInboundRequest request = AlterInboundRequest.newBuilder().setTag(tag)
                .setOperation(convertToTypedMessage(operation)).build();
        AlterInboundResponse ignored = handlerServiceBlockingStub.alterInbound(request);
    }

    /**
     * 为 Vmess Inbound 删除用户
     *
     * @param tag   标签名
     * @param email email
     */
    public void removeVmessInboundUser(String tag, @NotNull String email) {
        RemoveUserOperation operation = RemoveUserOperation.newBuilder().setEmail(email).build();
        AlterInboundRequest request = AlterInboundRequest.newBuilder().setTag(tag)
                .setOperation(convertToTypedMessage(operation)).build();
        AlterInboundResponse ignored = handlerServiceBlockingStub.alterInbound(request);
    }

    /**
     * 添加一个 Outbound 配置
     *
     * @param outbound 配置信息
     */
    public void addOutbound(@NotNull Outbound outbound) throws AppException {
        OutboundHandlerConfig outboundConfig = outbound.toOutboundHandlerConfig();
        AddOutboundRequest request = AddOutboundRequest.newBuilder().setOutbound(outboundConfig).build();
        AddOutboundResponse ignore = handlerServiceBlockingStub.addOutbound(request);
    }

    /**
     * 删除一个 Outbound 配置
     *
     * @param tag 要删除的 Outbound 的标签名
     */
    public void removeOutbound(String tag) {
        RemoveOutboundRequest request = RemoveOutboundRequest.newBuilder().setTag(tag).build();
        RemoveOutboundResponse ignored = handlerServiceBlockingStub.removeOutbound(request);
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
