package com.ev.nacos.order.server.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-server")
public interface UserServerClient {

    @GetMapping(value = "provider/service")
    String providerService();

}
