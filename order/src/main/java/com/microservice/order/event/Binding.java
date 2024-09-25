package com.microservice.order.event;

public enum Binding {

    PRODUCT_CREATED("product-created-out"),
    PRODUCT_DELETED("product-deleted-out"),
    ORDER_PLACED("order-out");

    String value;

    private Binding(String value) {
        this.value = value;
    }
}
