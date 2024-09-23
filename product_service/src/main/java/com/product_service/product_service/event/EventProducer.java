package com.product_service.product_service.event;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
public class EventProducer {


    private final StreamBridge streamBridge;

    public EventProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendMessage(MyEvent event, Binding binding) {
        Message message = MessageBuilder
                .withPayload(event)
                .setHeader("partitionKey", event.getKey())
                .build();
        streamBridge.send(binding.value, message);
    }
}