package me.mclee.v2ray.panel.entity.v2ray.log;

import lombok.Getter;
import lombok.Setter;

/**
 * config.log
 */
@Setter
@Getter
public class Log {
    private String access;
    private String error;
    private LogLevel loglevel;
}
