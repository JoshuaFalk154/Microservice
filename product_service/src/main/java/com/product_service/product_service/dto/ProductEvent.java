package com.product_service.product_service.dto;

import java.util.Date;

public record ProductEvent(Long id, String owner_id, String name, Double price,
                           String SKU, Date createdAt) {
}
