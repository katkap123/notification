package com.katta.notification.notification.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katta.notification.notification.dto.CreateNotificationRequest;
import com.katta.notification.notification.entity.Notification;
import com.katta.notification.notification.service.NotificationService;
import com.katta.notification.security.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/notifications")
public class AdminNotificationController {

    private final NotificationService notificationService;
    private final JwtService jwtService;

    public AdminNotificationController(
            NotificationService notificationService,
            JwtService jwtService) {
        this.notificationService = notificationService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(
            @Valid @RequestBody CreateNotificationRequest request,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        UUID userId = jwtService.extractUserId(token);

        Notification notification =
                notificationService.createNotification(request, userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notification);
    }
}