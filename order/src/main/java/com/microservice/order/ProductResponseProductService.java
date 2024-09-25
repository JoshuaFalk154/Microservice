package com.microservice.order;

public record ProductResponseProductService(Long id, String owner_id, String name, String description, Double price, String SKU) {
}
