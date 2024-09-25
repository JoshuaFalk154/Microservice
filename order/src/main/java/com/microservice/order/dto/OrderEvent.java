package com.microservice.order.dto;

import java.util.Date;

public record OrderEvent(Long id, String user_id, String productSKU, Long quantity, Double totalPrice, Date createdAt, Date updatedAt) {
}
