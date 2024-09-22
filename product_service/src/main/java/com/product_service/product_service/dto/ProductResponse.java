package com.product_service.product_service.dto;

public record ProductResponse(Long id, String owner_id, String name, String description, Double price, String SKU) {
}
