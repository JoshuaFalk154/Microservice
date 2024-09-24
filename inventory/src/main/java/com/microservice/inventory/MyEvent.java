package java.com.microservice.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MyEvent<K, T> {
    public enum Type {CREATE, DELETE}
    private Type eventType;
    private K key;
    private T data;

    @Builder.Default
    private ZonedDateTime eventCreatedAt = ZonedDateTime.now();
}