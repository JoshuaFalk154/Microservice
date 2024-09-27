package com.microservice.order;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ProductServiceClient {


    @GetExchange("/api/v1/product/{SKU}")
    @CircuitBreaker(name = "product", fallbackMethod = "fallbackMethod")
    @Retry(name = "product")
    ProductResponseProductService getProductFromProductService(@PathVariable String SKU);

    default ProductResponseProductService fallbackMethod(String SKU, Throwable t) {
        throw new RuntimeException("Can not get Product from product service", t);
    }
}
