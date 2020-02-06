package me.mclee.v2ray.panel.entity.v2ray.streamsettings;

public class Sockopt {
    private Integer mark;
    private Boolean tcpFastOpen;
    private String tproxy;

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Boolean getTcpFastOpen() {
        return tcpFastOpen;
    }

    public void setTcpFastOpen(Boolean tcpFastOpen) {
        this.tcpFastOpen = tcpFastOpen;
    }

    public String getTproxy() {
        return tproxy;
    }

    public void setTproxy(String tproxy) {
        this.tproxy = tproxy;
    }
}
