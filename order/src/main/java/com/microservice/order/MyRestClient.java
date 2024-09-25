package com.microservice.order;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface MyRestClient {

    @GetExchange("/api/v1/product/{SKU}")
    ProductResponse getProductFromProductService(@PathVariable String SKU);


}
