package com.product_service.product_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class Product {

//    @GetMapping("/product")
//    public String getProduct() {
//        return "hello world from product-service!";
//    }

    @GetMapping("/product")
    public String getProduct(@RequestHeader("email") String email) {
        return "hello" + email;
    }

}
