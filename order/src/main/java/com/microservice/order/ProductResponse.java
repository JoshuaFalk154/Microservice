package com.microservice.order;

public record ProductResponse(Long id, String owner_id, String name, String description, Double price, String SKU) {
}
