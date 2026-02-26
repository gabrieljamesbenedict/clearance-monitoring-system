package com.porado.clearance_monitoring_system.backend.dto;

public record StudentRegistrationRequest(

        // User generic fields
        String lastname,
        String firstname,
        String middlename,
        String email,
        String password,

        // Student specific fields
        String studentNumber,
        Long schoolId,
        Long programId
) {}