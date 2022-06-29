package com.ev.nacos.app.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("gateway/config")
@RefreshScope
public class ConfigController {

    @Value("${spring.application.name}")
    private String serverName;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() {
        System.out.println("=============nacos get gateway config ==========");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("server:").append(serverPort).append("/").append(serverName);
        return stringBuffer.toString();
    }
}
