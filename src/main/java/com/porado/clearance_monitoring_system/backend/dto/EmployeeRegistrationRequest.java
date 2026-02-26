package com.porado.clearance_monitoring_system.backend.dto;

public record EmployeeRegistrationRequest(

        // User generic fields
        String lastname,
        String firstname,
        String middlename,
        String email,
        String password,

        // Employee specific fields
        String employeeNumber

) {}