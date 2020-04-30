package me.mclee.v2ray.panel.entity.v2ray.transport;

import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.domainsocket.DsSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.http.HttpSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.kcp.KcpSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.quic.QuicSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.tcp.TcpSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.websocket.WsSettings;

@Setter
@Getter
public class Transport {
    private TcpSettings tcpSettings;
    private KcpSettings kcpSettings;
    private WsSettings wsSettings;
    private HttpSettings httpSettings;
    private DsSettings dsSettings;
    private QuicSettings quicSettings;
}
