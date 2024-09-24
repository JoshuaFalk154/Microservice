package com.microservice.inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class Listen {

    private final ProductService productService;



    @Bean
    public Consumer<Message<MyEvent<Long, ProductEvent>>> messageProcessor() {
        return message -> {
            log.info("received message {} in message processor method", message);

            MyEvent<Long, ProductEvent> event = message.getPayload();
            switch (event.getEventType()) {
                case CREATE:
                    ProductEvent productEvent = event.getData();
                    Product product = Product.builder()
                            .id(productEvent.id())
                            .owner_id(productEvent.owner_id())
                            .name(productEvent.name())
                            .SKU(productEvent.SKU())
                            .build();
                    productService.createProductIdempotent(product);
                    break;
                case DELETE:
                    productService.deleteProductIdempotent(event.getData().SKU());
                    break;
                default:
                    String errorMessage = "Incorrect event type: " +
                            event.getEventType() +
                            ", expected CREATE or DELETE event";
                    throw new RuntimeException(errorMessage);
            }
            };

    }
}