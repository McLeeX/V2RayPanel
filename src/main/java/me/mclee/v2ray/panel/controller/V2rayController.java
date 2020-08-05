package me.mclee.v2ray.panel.controller;

import lombok.extern.slf4j.Slf4j;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ResponseData;
import me.mclee.v2ray.panel.entity.v2ray.Config;
import me.mclee.v2ray.panel.service.V2rayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v2ray")
@RestController
@Slf4j
public class V2rayController {

    @Autowired
    private V2rayService v2rayService;

    @GetMapping
    @RequestMapping("/config")
    public ResponseData<Config> config() throws AppException {
        Config config = v2rayService.getConfig();
        return ResponseData.success(config);
    }

    @GetMapping
    @RequestMapping("/status")
    public ResponseData<Boolean> status() {
        return ResponseData.success(v2rayService.isRunning());
    }
}
