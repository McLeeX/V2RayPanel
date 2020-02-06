package me.mclee.v2ray.panel.entity.v2ray.streamsettings;

import java.util.List;

public class Certificate {
    private Usage usage;
    private String certificateFile;
    private String keyFile;
    private List<String> certificate;
    private List<String> key;

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public String getCertificateFile() {
        return certificateFile;
    }

    public void setCertificateFile(String certificateFile) {
        this.certificateFile = certificateFile;
    }

    public String getKeyFile() {
        return keyFile;
    }

    public void setKeyFile(String keyFile) {
        this.keyFile = keyFile;
    }

    public List<String> getCertificate() {
        return certificate;
    }

    public void setCertificate(List<String> certificate) {
        this.certificate = certificate;
    }

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }
}
