package com.ev.nacos.order.server.controller;

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

    @Value("${user.tag}")
    private String userTag;

    @Value("${user.name}")
    private String userName;

    @Value("${user.age}")
    private String userAge;

    @Value("${user.addr}")
    private String userAddr;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() {
        System.out.println("=============nacos get test msg ==========");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(userTag)
                .append("\n\r UserName:").append(userName)
                .append(",age:").append(userAge)
                .append(",addr:").append(userAddr)
                .append("\n").append(testMsg);
        return stringBuffer.toString();
    }
}
