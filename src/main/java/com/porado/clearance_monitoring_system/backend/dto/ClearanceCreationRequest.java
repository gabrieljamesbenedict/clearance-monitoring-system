package com.porado.clearance_monitoring_system.backend.dto;

public record ClearanceCreationRequest (
        Long studentId,
        String purpose,
        String academicYear,
        String semester
) {}
