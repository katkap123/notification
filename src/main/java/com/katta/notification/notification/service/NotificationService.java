package com.katta.notification.notification.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.katta.notification.notification.dto.CreateNotificationRequest;
import com.katta.notification.notification.entity.Notification;
import com.katta.notification.notification.event.NotificationCreatedEvent;
import com.katta.notification.notification.kafka.NotificationProducer;
import com.katta.notification.notification.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationProducer notificationProducer;

    public NotificationService(
            NotificationRepository notificationRepository,
            NotificationProducer notificationProducer) {

        this.notificationRepository = notificationRepository;
        this.notificationProducer = notificationProducer;
    }

    public Notification createNotification(
            CreateNotificationRequest request,
            UUID createdBy) {

        Notification notification = new Notification();

        notification.setTitle(request.title());
        notification.setMessage(request.message());
        notification.setCreatedBy(createdBy);
        notification.setCreatedAt(LocalDateTime.now());

        Notification savedNotification =
                notificationRepository.save(notification);

        NotificationCreatedEvent event =
                new NotificationCreatedEvent(
                        savedNotification.getId(),
                        savedNotification.getTitle(),
                        savedNotification.getMessage(),
                        savedNotification.getCreatedBy(),
                        savedNotification.getCreatedAt()
                );

        notificationProducer.publish(event);

        return savedNotification;
    }
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAllByOrderByCreatedAtDesc();
    }
}