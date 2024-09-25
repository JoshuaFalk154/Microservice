package com.microservice.inventory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductUpdate {
    private Long stock;
    private String location;
}
