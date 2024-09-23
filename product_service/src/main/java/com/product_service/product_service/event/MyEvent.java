package com.product_service.product_service.event;

import lombok.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MyEvent<K, T> {
    public enum Type {CREATE, DELETE}
    private MyEvent.Type eventType;
    private K key;
    private T data;

    @Builder.Default
    private ZonedDateTime eventCreatedAt = ZonedDateTime.now();
}