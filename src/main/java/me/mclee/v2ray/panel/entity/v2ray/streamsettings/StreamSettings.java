package me.mclee.v2ray.panel.entity.v2ray.streamsettings;

import me.mclee.v2ray.panel.entity.v2ray.streamsettings.domainsocket.DsSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.http.HttpSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.kcp.KcpSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.quic.QuicSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.tcp.TcpSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.tls.TlsSettings;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.websocket.WsSettings;

public class StreamSettings {
    private Network network;
    private Security security;
    private TlsSettings tlsSettings;
    private TcpSettings tcpSettings;
    private KcpSettings kcpSettings;
    private WsSettings wsSettings;
    private HttpSettings httpSettings;
    private DsSettings dsSettings;
    private QuicSettings quicSettings;
    private Sockopt sockopt;

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public TlsSettings getTlsSettings() {
        return tlsSettings;
    }

    public void setTlsSettings(TlsSettings tlsSettings) {
        this.tlsSettings = tlsSettings;
    }

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

    public Sockopt getSockopt() {
        return sockopt;
    }

    public void setSockopt(Sockopt sockopt) {
        this.sockopt = sockopt;
    }
}
