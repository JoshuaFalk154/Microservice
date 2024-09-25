package com.microservice.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Mapper {

    public ProductResponse productToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getOwner_id(),
                product.getName(),
                product.getStock(),
                product.getLocation(),
                product.getSKU(),
                product.createdAt,
                product.updatedAt
        );
    }

}
