package com.katta.notification.notification.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record AttendanceRecordedEvent(
        UUID attendanceId,
        UUID studentId,
        UUID classId,
        String className,
        LocalDate attendanceDate,
        AttendanceStatus status,
        UUID recordedBy,
        LocalDateTime recordedAt
) {
}