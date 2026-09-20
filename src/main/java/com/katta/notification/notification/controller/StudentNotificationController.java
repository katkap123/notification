package com.katta.notification.notification.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.katta.notification.notification.entity.Notification;
import com.katta.notification.notification.service.NotificationService;

@RestController
@RequestMapping("/api/student/notifications")
public class StudentNotificationController {

    private final NotificationService notificationService;

    public StudentNotificationController(
            NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications() {

        List<Notification> notifications =
                notificationService.getAllNotifications();

        return ResponseEntity.ok(notifications);
    }
}