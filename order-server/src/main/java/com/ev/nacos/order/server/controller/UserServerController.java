package com.ev.nacos.order.server.controller;

import com.ev.nacos.order.server.remote.UserServerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServerController {
    private static final Logger log = LoggerFactory.getLogger(UserServerController.class);

    @Autowired
    private UserServerClient userServerClient;

    @GetMapping("user/client/service")
    public String providerService(){
        log.info("order-server --> user-server client");
        return userServerClient.providerService();
    }
}
