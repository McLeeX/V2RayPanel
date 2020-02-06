package me.mclee.v2ray.panel.entity.v2ray.streamsettings.tls;

import me.mclee.v2ray.panel.entity.v2ray.streamsettings.Certificate;

import java.util.List;

public class TlsSettings {
    private String serverName;
    private Boolean allowInsecure;
    private List<String> alpn;
    private List<Certificate> certificates;
    private Boolean disableSystemRoot;
    private Boolean allowInsecureCiphers;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Boolean getAllowInsecure() {
        return allowInsecure;
    }

    public void setAllowInsecure(Boolean allowInsecure) {
        this.allowInsecure = allowInsecure;
    }

    public List<String> getAlpn() {
        return alpn;
    }

    public void setAlpn(List<String> alpn) {
        this.alpn = alpn;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public Boolean getDisableSystemRoot() {
        return disableSystemRoot;
    }

    public void setDisableSystemRoot(Boolean disableSystemRoot) {
        this.disableSystemRoot = disableSystemRoot;
    }

    public Boolean getAllowInsecureCiphers() {
        return allowInsecureCiphers;
    }

    public void setAllowInsecureCiphers(Boolean allowInsecureCiphers) {
        this.allowInsecureCiphers = allowInsecureCiphers;
    }
}
