package com.microservice.inventory;

import java.util.Date;

public record ProductResponse(Long id, String owner_id, String name, Long stock, String location, String SKU, Date createdAt, Date updatedAt) {
}
