package com.katta.notification.notification.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.katta.notification.notification.event.NotificationCreatedEvent;

@Service
public class NotificationProducer {

    private static final String TOPIC = "notification.created";

    private final KafkaTemplate<String, NotificationCreatedEvent> kafkaTemplate;

    public NotificationProducer(
            KafkaTemplate<String, NotificationCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(NotificationCreatedEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.notificationId().toString(),
                event
        );
    }
}