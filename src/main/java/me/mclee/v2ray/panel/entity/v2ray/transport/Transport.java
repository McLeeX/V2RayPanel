package me.mclee.v2ray.panel.entity.v2ray.transport;

import me.mclee.v2ray.panel.entity.v2ray.streamsettings.domainsocket.DsSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.http.HttpSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.kcp.KcpSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.quic.QuicSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.tcp.TcpSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.websocket.WsSettings;

public class Transport {
    private TcpSettings tcpSettings;
    private KcpSettings kcpSettings;
    private WsSettings wsSettings;
    private HttpSettings httpSettings;
    private DsSettings dsSettings;
    private QuicSettings quicSettings;

    public TcpSettings getTcpSettings() {
        return tcpSettings;
    }

    public void setTcpSettings(TcpSettings tcpSettings) {
        this.tcpSettings = tcpSettings;
    }

    public KcpSettings getKcpSettings() {
        return kcpSettings;
    }

    public void setKcpSettings(KcpSettings kcpSettings) {
        this.kcpSettings = kcpSettings;
    }

    public WsSettings getWsSettings() {
        return wsSettings;
    }

    public void setWsSettings(WsSettings wsSettings) {
        this.wsSettings = wsSettings;
    }

    public HttpSettings getHttpSettings() {
        return httpSettings;
    }

    public void setHttpSettings(HttpSettings httpSettings) {
        this.httpSettings = httpSettings;
    }

    public DsSettings getDsSettings() {
        return dsSettings;
    }

    public void setDsSettings(DsSettings dsSettings) {
        this.dsSettings = dsSettings;
    }

    public QuicSettings getQuicSettings() {
        return quicSettings;
    }

    public void setQuicSettings(QuicSettings quicSettings) {
        this.quicSettings = quicSettings;
    }
}
