package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.mtproto;

import me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.InboundSettings;

import java.util.List;

public class MTProto extends InboundSettings {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
