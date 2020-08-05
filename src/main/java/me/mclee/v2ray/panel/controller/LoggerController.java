package me.mclee.v2ray.panel.controller;

import lombok.extern.slf4j.Slf4j;
import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ResponseData;
import me.mclee.v2ray.panel.service.V2rayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2ray/logger")
@Slf4j
public class LoggerController {

    @Autowired
    private V2rayService v2rayService;

    @GetMapping("/restart")
    public ResponseData<Void> restartLogger() throws AppException {
        v2rayService.restartLogger();
        return ResponseData.success();
    }

}
