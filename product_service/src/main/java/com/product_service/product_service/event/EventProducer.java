package com.product_service.product_service.event;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
public class EventProducer {


    private final StreamBridge streamBridge;

    public EventProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendEvent(MyEvent event, Binding binding) {
        streamBridge.send(binding.value, event);
    }
}