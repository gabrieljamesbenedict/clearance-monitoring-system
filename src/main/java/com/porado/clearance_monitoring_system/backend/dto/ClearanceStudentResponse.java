package com.porado.clearance_monitoring_system.backend.dto;

import com.porado.clearance_monitoring_system.backend.model.Clearance;

import java.time.Instant;

public record ClearanceStudentResponse(
        Long clearanceId,
        String purpose,
        String academicYear,
        String semester,
        Instant createdAt,
        Instant deletedAt
) {
    public static ClearanceStudentResponse toStudentClearance(Clearance clearance) {
        return new ClearanceStudentResponse(
                clearance.getClearanceId(),
                clearance.getPurpose(),
                clearance.getAcademicYear(),
                clearance.getSemester(),
                clearance.getCreatedAt(),
                clearance.getDeletedAt()
        );
    }
}
