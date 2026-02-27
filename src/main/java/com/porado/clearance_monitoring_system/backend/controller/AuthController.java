package com.porado.clearance_monitoring_system.backend.controller;

import com.porado.clearance_monitoring_system.backend.dto.EmployeeRegistrationRequest;
import com.porado.clearance_monitoring_system.backend.dto.MeResponse;
import com.porado.clearance_monitoring_system.backend.dto.StudentRegistrationRequest;
import com.porado.clearance_monitoring_system.backend.model.Program;
import com.porado.clearance_monitoring_system.backend.model.School;
import com.porado.clearance_monitoring_system.backend.model.Student;
import com.porado.clearance_monitoring_system.backend.model.User;
import com.porado.clearance_monitoring_system.backend.service.AuthService;
import com.porado.clearance_monitoring_system.backend.service.ProgramService;
import com.porado.clearance_monitoring_system.backend.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //TODO: fix auth

    @GetMapping("/me")
    public ResponseEntity<MeResponse> me () {
        MeResponse meResponse = authService.me();
        return ResponseEntity.ok(meResponse);
    }

    @PostMapping("/register/student")
    public ResponseEntity<String> registerStudent(
            @RequestBody StudentRegistrationRequest request) {

        authService.registerStudent(request);

        return ResponseEntity.ok("Student registered successfully");
    }

    @PostMapping("/register/employee")
    public ResponseEntity<String> registerEmployee(
            @RequestBody EmployeeRegistrationRequest request) {

        authService.registerEmployee(request);

        return ResponseEntity.ok("Employee registered successfully");
    }

}
