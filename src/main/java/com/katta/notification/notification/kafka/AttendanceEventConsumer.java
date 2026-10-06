package com.katta.notification.notification.kafka;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.katta.notification.notification.client.StudentContactClient;
import com.katta.notification.notification.dto.StudentContactResponse;
import com.katta.notification.notification.event.AttendanceRecordedEvent;
import com.katta.notification.notification.event.AttendanceStatus;

@Component
public class AttendanceEventConsumer {

    private final StudentContactClient studentContactClient;

    public AttendanceEventConsumer(
            StudentContactClient studentContactClient) {
        this.studentContactClient = studentContactClient;
    }

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

        List<StudentContactResponse> contacts =
                studentContactClient.getContacts(event.studentId());

        System.out.println(
                "Found " + contacts.size()
                        + " contact(s) for student "
                        + event.studentId()
        );

        for (StudentContactResponse contact : contacts) {
            System.out.println(
                    "Contact: "
                            + contact.firstName()
                            + " | relationship=" + contact.relationship()
                            + " | emailEnabled="
                            + contact.emailNotificationsEnabled()
                            + " | smsEnabled="
                            + contact.smsNotificationsEnabled()
            );
        }
    }
}