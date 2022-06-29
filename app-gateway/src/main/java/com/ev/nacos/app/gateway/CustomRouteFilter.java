package com.ev.nacos.app.gateway;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Objects;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

public class CustomRouteFilter extends AbstractGatewayFilterFactory {
    private static final Log log = LogFactory.getLog(CustomRouteFilter.class);

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            //获取url地址
            ServerHttpRequest request = exchange.getRequest();
            String rawPath = request.getURI().getRawPath();
            HttpHeaders headers = request.getHeaders();
            HttpMethod method = request.getMethod();
            //请求参数
            MultiValueMap< String, String > queryParams = request.getQueryParams();
            String appId = headers.get("APP_ID").get(0);

            HashMap<String,String> appUriMap = new HashMap<>();
            appUriMap.put("order-server-A","http://127.0.0.1:6001");
            appUriMap.put("user-server-A","http://127.0.0.1:6002");
            appUriMap.put("order-server-B","http://127.0.0.1:6061");
            appUriMap.put("user-server-B","http://127.0.0.1:6062");

            if(Objects.isNull(appUriMap) || StringUtils.isEmpty(appUriMap.get(appId))){
                throw new NullPointerException("找不到目标服务");
            }
            log.info(appId + ": " + appUriMap.get(appId));
            URI uri = UriComponentsBuilder.fromHttpUrl(appUriMap.get(appId)+rawPath).queryParams(queryParams).build().toUri();

            //替换新的url地址
            ServerHttpRequest serverHttpRequest = request.mutate().uri(uri).method(method).headers(
                    httpHeaders -> httpHeaders=headers).build();
            Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
            //从新设置Route地址
            Route newRoute = Route.async().asyncPredicate(route.getPredicate())
                    .filters(route.getFilters()).id(route.getId())
                    .order(route.getOrder()).uri(uri).build();
            exchange.getAttributes().put(GATEWAY_ROUTE_ATTR,newRoute);
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        };
    }

}
