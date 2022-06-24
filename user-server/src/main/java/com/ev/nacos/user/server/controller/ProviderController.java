package com.ev.nacos.user.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    private static final Logger log = LoggerFactory.getLogger(ProviderController.class);

    @GetMapping("provider/service")
    public String providerService(){
        log.info("user-server provider service invoke");
        return "user-server provider service invoke";
    }
}
