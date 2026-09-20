package com.katta.notification.notification.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katta.notification.notification.event.NotificationCreatedEvent;
import com.katta.notification.notification.kafka.NotificationProducer;

@RestController
@RequestMapping("/api/test/kafka")
public class KafkaTestController {

    private final NotificationProducer notificationProducer;

    public KafkaTestController(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> testKafka() {

        NotificationCreatedEvent event =
                new NotificationCreatedEvent(
                        UUID.randomUUID(),
                        "Notification API Test",
                        "Kafka is working from notification-api!",
                        UUID.randomUUID(),
                        LocalDateTime.now()
                );

        notificationProducer.publish(event);

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Kafka event published successfully"
                )
        );
    }
}