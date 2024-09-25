package com.microservice.order;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ProductServiceClient {

    @GetExchange("/api/v1/product/{SKU}")
    ProductResponseProductService getProductFromProductService(@PathVariable String SKU);

}
