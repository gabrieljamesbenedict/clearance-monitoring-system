package com.porado.clearance_monitoring_system.backend.controller;

import com.porado.clearance_monitoring_system.backend.dto.EmployeeRegistrationRequest;
import com.porado.clearance_monitoring_system.backend.dto.MeResponse;
import com.porado.clearance_monitoring_system.backend.dto.MessageResponse;
import com.porado.clearance_monitoring_system.backend.dto.StudentRegistrationRequest;
import com.porado.clearance_monitoring_system.backend.exception.StudentAlreadyExistsException;
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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/me")
    public ResponseEntity<MeResponse> me (Authentication authentication) {
        return ResponseEntity.ok(authService.me(authentication));
    }

    @PostMapping("/register/student")
    public ResponseEntity<MessageResponse> registerStudent(
            @RequestBody StudentRegistrationRequest request) {

        try {
            authService.registerStudent(request);
            return ResponseEntity.ok(new MessageResponse("Student registered successfully"));
        } catch (StudentAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Student already exists"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MessageResponse("Internal Server Error"));
        }
    }

    @PostMapping("/register/employee")
    public ResponseEntity<String> registerEmployee(
            @RequestBody EmployeeRegistrationRequest request) {

        authService.registerEmployee(request);

        return ResponseEntity.ok("Employee registered successfully");
    }

}
