package com.porado.clearance_monitoring_system.backend.dto;

import com.porado.clearance_monitoring_system.backend.model.User;
import com.porado.clearance_monitoring_system.backend.util.Role;

public record MeResponse(
        String lastname,
        String firstname,
        String middlename,
        Long userId,
        String email,
        Role role,
        String studentNumber,
        String employeeNumber
) {
    public MeResponse(User user, String studentNumber, String employeeNumber) {
        this(
                user.getLastname(),
                user.getFirstname(),
                user.getMiddlename(),
                user.getUserId(),
                user.getEmail(),
                user.getRole(),
                studentNumber,
                employeeNumber
        );
    }
}
