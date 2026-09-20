package com.katta.notification.notification.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katta.notification.notification.entity.Notification;

public interface NotificationRepository
        extends JpaRepository<Notification, UUID> {

    List<Notification> findAllByOrderByCreatedAtDesc();
}