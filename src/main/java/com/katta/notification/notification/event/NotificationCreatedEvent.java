package com.katta.notification.notification.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationCreatedEvent(
        UUID notificationId,
        String title,
        String message,
        UUID createdBy,
        LocalDateTime createdAt
) {
}