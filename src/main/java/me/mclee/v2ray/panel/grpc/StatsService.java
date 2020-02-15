package me.mclee.v2ray.panel.grpc;

import com.v2ray.core.app.stats.command.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class StatsService implements Closeable {
    private final ManagedChannel channel;
    private final StatsServiceGrpc.StatsServiceBlockingStub statsServiceBlockingStub;

    public StatsService(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        statsServiceBlockingStub = StatsServiceGrpc.newBlockingStub(channel);
    }

    public Long getStats(String name, boolean reset) {
        GetStatsRequest request = GetStatsRequest.newBuilder().setName(name).setReset(reset).build();
        GetStatsResponse response = statsServiceBlockingStub.getStats(request);
        return Optional.ofNullable(response).map(GetStatsResponse::getStat).map(Stat::getValue).orElse(0L);
    }

    public List<Stat> queryStats(String pattern, boolean reset) {
        QueryStatsRequest request = QueryStatsRequest.newBuilder().setPattern(pattern).setReset(reset).build();
        QueryStatsResponse response = statsServiceBlockingStub.queryStats(request);
        return response.getStatList();
    }

    public SysStatsResponse getSysStats() {
        SysStatsRequest request = SysStatsRequest.newBuilder().buildPartial();
        return statsServiceBlockingStub.getSysStats(request);
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
