package com.porado.clearance_monitoring_system.backend.controller;

import com.porado.clearance_monitoring_system.backend.dto.StudentRegistrationRequest;
import com.porado.clearance_monitoring_system.backend.model.Program;
import com.porado.clearance_monitoring_system.backend.model.School;
import com.porado.clearance_monitoring_system.backend.model.Student;
import com.porado.clearance_monitoring_system.backend.service.AuthService;
import com.porado.clearance_monitoring_system.backend.service.ProgramService;
import com.porado.clearance_monitoring_system.backend.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/student")
    public ResponseEntity<String> registerStudent(
            @RequestBody StudentRegistrationRequest request) {

        authService.registerStudent(request);

        return ResponseEntity.ok("Student registered successfully");
    }

}
