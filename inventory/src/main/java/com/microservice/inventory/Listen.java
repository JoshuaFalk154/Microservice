package java.com.microservice.inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class Listen {



    @Bean
    public Consumer<MyEvent<Long, ProductEvent>> messageProcessor() {
        return event -> {
            switch (event.getEventType()) {
                case CREATE:
                    ProductEvent productEvent = event.getData();
                    Product product = Product.builder()
                            .id(productEvent.id())
                            .owner_id(productEvent.owner_id())
                            .name(productEvent.name())
                            .SKU(productEvent.SKU())
                            .build();


            }
            };

    }
}