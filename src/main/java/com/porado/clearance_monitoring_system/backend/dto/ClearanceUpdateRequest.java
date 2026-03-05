package com.porado.clearance_monitoring_system.backend.dto;

public record ClearanceUpdateRequest(
        Long clearanceId,
        String purpose,
        String academicYear,
        String semester
) {}
