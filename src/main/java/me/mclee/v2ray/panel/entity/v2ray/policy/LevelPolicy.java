package me.mclee.v2ray.panel.entity.v2ray.policy;

public class LevelPolicy {
    private int handshake = 4;
    private int connIdle = 300;
    private int uplinkOnly = 2;
    private int downlinkOnly = 5;
    private Boolean statusUserUplink;
    private Boolean statusUserDownlink;
    private long bufferSize;

    public int getHandshake() {
        return handshake;
    }

    public void setHandshake(int handshake) {
        this.handshake = handshake;
    }

    public int getConnIdle() {
        return connIdle;
    }

    public void setConnIdle(int connIdle) {
        this.connIdle = connIdle;
    }

    public int getUplinkOnly() {
        return uplinkOnly;
    }

    public void setUplinkOnly(int uplinkOnly) {
        this.uplinkOnly = uplinkOnly;
    }

    public int getDownlinkOnly() {
        return downlinkOnly;
    }

    public void setDownlinkOnly(int downlinkOnly) {
        this.downlinkOnly = downlinkOnly;
    }

    public Boolean getStatusUserUplink() {
        return statusUserUplink;
    }

    public void setStatusUserUplink(Boolean statusUserUplink) {
        this.statusUserUplink = statusUserUplink;
    }

    public Boolean getStatusUserDownlink() {
        return statusUserDownlink;
    }

    public void setStatusUserDownlink(Boolean statusUserDownlink) {
        this.statusUserDownlink = statusUserDownlink;
    }

    public long getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(long bufferSize) {
        this.bufferSize = bufferSize;
    }
}
