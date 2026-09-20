package com.katta.notification.notification.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.katta.notification.notification.event.NotificationCreatedEvent;

@Service
public class NotificationConsumer {

    @KafkaListener(
            topics = "notification.created",
            groupId = "notification-service"
    )
    public void consume(NotificationCreatedEvent event) {

        System.out.println(
                "Notification API received event: " + event
        );
    }
}