package com.ev.nacos.user.server.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "order-server")
public interface OrderServerClient {

    @GetMapping(value = "provider/service")
    String providerService();

}
