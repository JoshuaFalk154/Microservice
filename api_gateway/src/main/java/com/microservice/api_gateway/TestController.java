package com.microservice.api_gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/api/api-gateway")
public class TestController {

    @GetMapping
    public String test() {
        return "hello from api-gateway";
    }
}
