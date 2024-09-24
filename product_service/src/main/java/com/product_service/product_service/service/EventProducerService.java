package com.product_service.product_service.service;

import com.product_service.product_service.entities.Product;
import com.product_service.product_service.event.Binding;
import com.product_service.product_service.event.EventProducer;
import com.product_service.product_service.event.MyEvent;
import com.product_service.product_service.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventProducerService {

    private final EventProducer eventProducer;
    private final Mapper mapper;

    public void sendMessageProductCreated(Product product) {


        MyEvent event = MyEvent.builder()
                .key(product.getId())
                .eventType(MyEvent.Type.CREATE)
                .data(mapper.productToProductEvent(product))
                .build();
        eventProducer.sendMessage(event, Binding.PRODUCT_CREATED);
    }

    public void sendMessageProductDeleted(Product product) {
        MyEvent event = MyEvent.builder()
                .key(product.getId())
                .eventType(MyEvent.Type.DELETE)
                .data(mapper.productToProductEvent(product))
                .build();
        eventProducer.sendMessage(event, Binding.PRODUCT_DELETED);
    }
}
