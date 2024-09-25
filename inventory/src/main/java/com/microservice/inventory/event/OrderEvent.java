package com.microservice.inventory.event;

import java.util.Date;

public record OrderEvent(Long id, String user_id, String productSKU, Long quantity, Double totalPrice, Date createdAt, Date updatedAt) {
}
