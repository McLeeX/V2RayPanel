package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.vmess;

import java.util.List;

public class Vnext {
    private String address;
    private Integer port;
    private List<User> users;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
