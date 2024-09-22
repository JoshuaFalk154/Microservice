package com.microservice.api_gateway.route;

import com.microservice.api_gateway.filter.UserHeaderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

import static org.springframework.web.servlet.function.RequestPredicates.path;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;


@Configuration
@RequiredArgsConstructor
public class Routes {

    @Value("${productservice.route}")
    private String productServiceUrl;

    private final UserHeaderFilter userHeaderFilter;

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return route("product_service")
                .route(path("/api/v1/product"), http(productServiceUrl))
                .route(path("/api/v1/product/*"), http(productServiceUrl))
                .before(userHeaderFilter.addUserHeader())
                .build();
    }
}
