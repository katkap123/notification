package com.katta.notification.notification.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminTestController {

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> testAdmin() {

        return ResponseEntity.ok(
            Map.of(
                "message",
                "ADMIN authentication successful"
            )
        );
    }

    // @PostMapping("/notifications")
    // public ResponseEntity<Map<String, String>> testNotification() {
    //     return ResponseEntity.ok(
    //         Map.of("message", "Notification POST endpoint works")
    //     );
    // }
}