package com.microservice.order.event;

import com.microservice.order.Order;
import com.microservice.order.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventProducerService {

    private final EventProducer eventProducer;

    public void sendMessageOrderPlaced(Order order) {
        OrderEvent orderEvent = new OrderEvent(
                order.getId(),
                order.getUser_id(),
                order.getProductSKU(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );


        MyEvent myEvent = MyEvent.builder()
                .eventType(MyEvent.Type.CREATE)
                .key(order.getId())
                .data(order)
                .build();

        eventProducer.sendMessage(myEvent, Binding.ORDER_PLACED);
    }

}
