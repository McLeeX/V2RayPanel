package me.mclee.v2ray.panel.entity.v2ray.streamsettings.tls;

import lombok.Getter;
import lombok.Setter;
import me.mclee.v2ray.panel.entity.v2ray.streamsettings.Certificate;

import java.util.List;

@Setter
@Getter
public class TlsSettings {
    private String serverName;
    private Boolean allowInsecure;
    private List<String> alpn;
    private List<Certificate> certificates;
    private Boolean disableSystemRoot;
    private Boolean allowInsecureCiphers;
}
