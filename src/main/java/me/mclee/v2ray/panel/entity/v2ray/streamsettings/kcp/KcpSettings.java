package me.mclee.v2ray.panel.entity.v2ray.streamsettings.kcp;

import me.mclee.v2ray.panel.entity.v2ray.streamsettings.common.Header;

public class KcpSettings {
    private Integer mtu;
    private Integer tti;
    private Integer uplinkCapacity;
    private Integer downlinkCapacity;
    private Boolean congestion;
    private Integer readBufferSize;
    private Integer writeBufferSize;
    private Header header;

    public Integer getMtu() {
        return mtu;
    }

    public void setMtu(Integer mtu) {
        this.mtu = mtu;
    }

    public Integer getTti() {
        return tti;
    }

    public void setTti(Integer tti) {
        this.tti = tti;
    }

    public Integer getUplinkCapacity() {
        return uplinkCapacity;
    }

    public void setUplinkCapacity(Integer uplinkCapacity) {
        this.uplinkCapacity = uplinkCapacity;
    }

    public Integer getDownlinkCapacity() {
        return downlinkCapacity;
    }

    public void setDownlinkCapacity(Integer downlinkCapacity) {
        this.downlinkCapacity = downlinkCapacity;
    }

    public Boolean getCongestion() {
        return congestion;
    }

    public void setCongestion(Boolean congestion) {
        this.congestion = congestion;
    }

    public Integer getReadBufferSize() {
        return readBufferSize;
    }

    public void setReadBufferSize(Integer readBufferSize) {
        this.readBufferSize = readBufferSize;
    }

    public Integer getWriteBufferSize() {
        return writeBufferSize;
    }

    public void setWriteBufferSize(Integer writeBufferSize) {
        this.writeBufferSize = writeBufferSize;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }
}
