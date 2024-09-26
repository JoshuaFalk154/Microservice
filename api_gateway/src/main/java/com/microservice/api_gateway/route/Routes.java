package com.microservice.api_gateway.route;

import com.microservice.api_gateway.filter.UserHeaderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

import static org.springframework.web.servlet.function.RequestPredicates.path;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;


@Configuration
@RequiredArgsConstructor
public class Routes {

    @Value("${productservice.route}")
    private String productServiceUrl;

    @Value("${inventoryservice.route}")
    private String inventoryServiceUrl;

    @Value("${orderservice.route}")
    private String orderServiceUrl;

    private final UserHeaderFilter userHeaderFilter;

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return route("product_service")
                .route(path("/api/v1/product"), http(productServiceUrl))
                .route(path("/api/v1/product/**"), http(productServiceUrl))
                .before(userHeaderFilter.addUserHeader())
                .filter(circuitBreaker("productServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return route("inventory_service")
                .route(path("/api/v1/inventory"), http(inventoryServiceUrl))
                .route(path("/api/v1/inventory/**"), http(inventoryServiceUrl))
                .before(userHeaderFilter.addUserHeader())
                .filter(circuitBreaker("inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return route("order_service")
                .route(path("/api/v1/order"), http(orderServiceUrl))
                .route(path("/api/v1/order/**"), http(orderServiceUrl))
                .before(userHeaderFilter.addUserHeader())
                .filter(circuitBreaker("orderServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallbackRoute")
                .GET("/fallbackRoute", request ->
                        ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service unavailable, please try again later"))
                .build();
    }
}
