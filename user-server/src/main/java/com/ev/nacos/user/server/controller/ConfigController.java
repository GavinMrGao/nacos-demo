package com.ev.nacos.user.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("config")
@RefreshScope
public class ConfigController {

    @Value("${test.msg}")
    private String testMsg;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() {
        System.out.println("=============nacos get test msg ==========");
        return testMsg;
    }
}
