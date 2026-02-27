package com.porado.clearance_monitoring_system.backend.service;

import com.porado.clearance_monitoring_system.backend.dto.EmployeeRegistrationRequest;
import com.porado.clearance_monitoring_system.backend.dto.MeResponse;
import com.porado.clearance_monitoring_system.backend.dto.StudentRegistrationRequest;
import com.porado.clearance_monitoring_system.backend.model.User;

public interface AuthService {
    MeResponse me();
    User registerStudent(StudentRegistrationRequest registrationRequest);
    User registerEmployee(EmployeeRegistrationRequest registrationRequest);
}
