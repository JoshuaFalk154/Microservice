package com.microservice.order;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class OrderResponse {
    private Long id;
    private String user_id;
    private String productSku;
    private Long quantity;
    private Double totalPrice;
    private Date createdAt;
    private Date updatedAt;
}
