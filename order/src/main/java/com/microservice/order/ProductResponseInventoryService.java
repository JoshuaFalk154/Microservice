package com.microservice.order;

import java.util.Date;

public record ProductResponseInventoryService(Long id, String owner_id, String name, Long stock, String location, String SKU, Date createdAt, Date updatedAt) {
}
