package com.porado.clearance_monitoring_system.backend.service;

import com.porado.clearance_monitoring_system.backend.dto.EmployeeRegistrationRequest;
import com.porado.clearance_monitoring_system.backend.dto.StudentRegistrationRequest;
import com.porado.clearance_monitoring_system.backend.model.Employee;
import com.porado.clearance_monitoring_system.backend.model.Student;
import com.porado.clearance_monitoring_system.backend.model.User;

public interface AuthService {
    User me();
    User registerStudent(StudentRegistrationRequest registrationRequest);
    User registerEmployee(EmployeeRegistrationRequest registrationRequest);
}
