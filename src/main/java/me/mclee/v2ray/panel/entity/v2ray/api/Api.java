package me.mclee.v2ray.panel.entity.v2ray.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * config.api
 */
@Setter
@Getter
public class Api {
    private String tag;
    private List<Service> services;
}
