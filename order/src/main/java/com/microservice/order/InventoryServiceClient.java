package com.microservice.order;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryServiceClient {

    @GetExchange("/api/v1/inventory/product/{SKU}")
    ProductResponseInventoryService getProductFromInventoryService(@PathVariable String SKU);

}
