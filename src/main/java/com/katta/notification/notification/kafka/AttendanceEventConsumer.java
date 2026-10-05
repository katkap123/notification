package com.katta.notification.notification.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.katta.notification.notification.event.AttendanceRecordedEvent;
import com.katta.notification.notification.event.AttendanceStatus;

@Component
public class AttendanceEventConsumer {

    @KafkaListener(
            topics = "attendance.recorded",
            groupId = "notification-service"
    )
    public void consume(AttendanceRecordedEvent event) {

        System.out.println(
                "Received attendance event: " + event
        );

        if (event.status() != AttendanceStatus.ABSENT) {
            System.out.println(
                    "Student is present. No parent notification required."
            );
            return;
        }

        System.out.println(
                "ABSENT student detected: " + event.studentId()
        );

        System.out.println(
                "Class: " + event.className()
                        + ", Date: " + event.attendanceDate()
        );
    }
}