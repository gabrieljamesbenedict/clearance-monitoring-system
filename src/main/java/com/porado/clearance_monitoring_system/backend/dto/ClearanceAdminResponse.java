package com.porado.clearance_monitoring_system.backend.dto;

import com.porado.clearance_monitoring_system.backend.model.Clearance;
import com.porado.clearance_monitoring_system.backend.util.Status;

import java.time.Instant;

public record ClearanceAdminResponse(
        Long clearanceId,
        String studentName,
        String studentNumber,
        String schoolName,
        String programName,
        String purpose,
        String academicYear,
        String semester,
        Status status,
        Instant createdAt
) {
    public static ClearanceAdminResponse toAdminClearance(Clearance clearance) {
        String fullName = clearance.getUser().getLastname() + ", " + 
                          clearance.getUser().getFirstname() + " " + 
                          clearance.getUser().getMiddlename();

        return new ClearanceAdminResponse(
                clearance.getClearanceId(),
                fullName,
                clearance.getUser().getStudent().getStudentNumber(),
                clearance.getUser().getStudent().getSchool().getName(),
                clearance.getUser().getStudent().getProgram().getName(),
                clearance.getPurpose(),
                clearance.getAcademicYear(),
                clearance.getSemester(),
                clearance.getStatus(),
                clearance.getCreatedAt()
        );
    }
}