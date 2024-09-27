package com.microservice.order;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryServiceClient {

    @GetExchange("/api/v1/inventory/product/{SKU}")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    ProductResponseInventoryService getProductFromInventoryService(@PathVariable String SKU);

    default ProductResponseInventoryService fallbackMethod(String SKU, Throwable t) {
        throw new RuntimeException("Can not get product from inventory service", t);
    }
}
