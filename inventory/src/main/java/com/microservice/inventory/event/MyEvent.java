package com.microservice.inventory.event;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyEvent<K, T> {
    public enum Type {CREATE, DELETE}
    private Type eventType;
    private K key;
    private T data;

    @Builder.Default
    private ZonedDateTime eventCreatedAt = ZonedDateTime.now();
}