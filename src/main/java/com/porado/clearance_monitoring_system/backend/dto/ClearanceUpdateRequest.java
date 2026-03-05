package com.porado.clearance_monitoring_system.backend.dto;

import com.porado.clearance_monitoring_system.backend.util.Status;

public record ClearanceUpdateRequest(
        Long clearanceId,
        String purpose,
        String academicYear,
        String semester,
        Status status
) {}
