package me.mclee.v2ray.panel.entity.v2ray.streamsettings;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Certificate {
    private Usage usage;
    private String certificateFile;
    private String keyFile;
    private List<String> certificate;
    private List<String> key;
}
