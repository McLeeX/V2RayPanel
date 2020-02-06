package me.mclee.v2ray.panel.entity.v2ray.outbounds.outboundsettings.http;

import me.mclee.v2ray.panel.entity.v2ray.Account;

import java.util.List;

public class Server {
    private String address;
    private Integer port;
    private List<Account> users;

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

    public List<Account> getUsers() {
        return users;
    }

    public void setUsers(List<Account> users) {
        this.users = users;
    }
}
