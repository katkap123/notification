package com.katta.notification.notification.dto;

import java.util.UUID;

public record StudentContactResponse(
        UUID id,
        UUID studentId,
        String firstName,
        String lastName,
        String relationship,
        String email,
        String phoneNumber,
        boolean primaryContact,
        boolean emailNotificationsEnabled,
        boolean smsNotificationsEnabled
) {
}