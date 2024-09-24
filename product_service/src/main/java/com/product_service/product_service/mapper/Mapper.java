package com.product_service.product_service.mapper;

import com.product_service.product_service.dto.ProductEvent;
import com.product_service.product_service.entities.Product;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public ProductEvent productToProductEvent(Product product) {
        return new ProductEvent(product.getId(),
                product.getOwner_id(),
                product.getName(),
                product.getPrice(),
                product.getSKU(),
                product.getCreatedAt()
        );
    }
}
