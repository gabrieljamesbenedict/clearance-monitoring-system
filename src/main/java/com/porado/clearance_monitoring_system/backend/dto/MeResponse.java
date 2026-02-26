package com.porado.clearance_monitoring_system.backend.dto;

import com.porado.clearance_monitoring_system.backend.util.Role;

public record MeResponse(
        Long userId,
        String email,
        Role role,
        String studentNumber,
        String employeeNumber
) {}
