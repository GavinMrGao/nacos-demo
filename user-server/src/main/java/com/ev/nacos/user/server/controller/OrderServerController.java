package com.ev.nacos.user.server.controller;

import com.ev.nacos.user.server.remote.OrderServerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderServerController {
    private static final Logger log = LoggerFactory.getLogger(OrderServerController.class);

    @Autowired
    private OrderServerClient orderServerClient;

    @GetMapping("order/client/service")
    public String providerService(){
        log.info("user-server --> order-server client");
        return orderServerClient.providerService();
    }
}
