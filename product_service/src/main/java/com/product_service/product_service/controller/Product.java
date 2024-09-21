package com.product_service.product_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Product {

    @GetMapping("/product")
    public String getProduct() {
        return "hello world from product-service!";
    }
}
