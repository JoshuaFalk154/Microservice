package com.microservice.inventory.event;

import java.util.Date;

public record ProductEvent(Long id, String owner_id, String name, Double price,
                           String SKU, Date createdAt) {
}
