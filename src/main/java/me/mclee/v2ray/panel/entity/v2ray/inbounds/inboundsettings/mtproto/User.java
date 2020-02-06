package me.mclee.v2ray.panel.entity.v2ray.inbounds.inboundsettings.mtproto;

public class User {
    private String emails;
    private Integer level;
    private String secret;

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
