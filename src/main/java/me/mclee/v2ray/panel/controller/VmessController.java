package me.mclee.v2ray.panel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VmessController {

    @GetMapping("/test")
    private String test() {
        return "test";
    }
}
