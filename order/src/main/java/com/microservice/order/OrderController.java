package com.microservice.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final MyRestClient myRestClient;

    @GetMapping
    public ProductResponse testConnection() {
        return  myRestClient.getProductFromProductService("E42afvfbvaWC");
    }

}
