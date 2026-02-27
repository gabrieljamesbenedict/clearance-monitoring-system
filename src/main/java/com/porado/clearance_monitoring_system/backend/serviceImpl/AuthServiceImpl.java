package com.porado.clearance_monitoring_system.backend.serviceImpl;

import com.porado.clearance_monitoring_system.backend.auth.MapuanUserDetails;
import com.porado.clearance_monitoring_system.backend.dto.EmployeeRegistrationRequest;
import com.porado.clearance_monitoring_system.backend.dto.LoginRequest;
import com.porado.clearance_monitoring_system.backend.dto.MeResponse;
import com.porado.clearance_monitoring_system.backend.dto.StudentRegistrationRequest;
import com.porado.clearance_monitoring_system.backend.exception.EmployeeNotFoundException;
import com.porado.clearance_monitoring_system.backend.exception.StudentNotFoundException;
import com.porado.clearance_monitoring_system.backend.exception.UnauthenticatedException;
import com.porado.clearance_monitoring_system.backend.model.*;
import com.porado.clearance_monitoring_system.backend.repository.EmployeeRepository;
import com.porado.clearance_monitoring_system.backend.repository.StudentRepository;
import com.porado.clearance_monitoring_system.backend.repository.UserRepository;
import com.porado.clearance_monitoring_system.backend.service.AuthService;
import com.porado.clearance_monitoring_system.backend.service.ProgramService;
import com.porado.clearance_monitoring_system.backend.service.SchoolService;
import com.porado.clearance_monitoring_system.backend.util.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final EmployeeRepository employeeRepository;

    private final SchoolService schoolService;
    private final ProgramService programService;

    private final PasswordEncoder passwordEncoder;
    //private final AuthenticationManager authenticationManager;

    @Override
    public MeResponse me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenticatedException();
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof MapuanUserDetails details) {
            User user = details.getUser();

            String studentNumber = null;
            String employeeNumber = null;

            if (user.getRole() == Role.ROLE_STUDENT) {
                studentNumber = studentRepository.findById(user.getUserId())
                        .orElseThrow(() -> new StudentNotFoundException("Student not found with Id=" + user.getUserId()))
                        .getStudentNumber();
            } else if (user.getRole() == Role.ROLE_EMPLOYEE) {
                employeeNumber = employeeRepository.findById(user.getUserId())
                        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with Id=" + user.getUserId()))
                        .getEmployeeNumber();
            }

            return new MeResponse(
                    user.getUserId(),
                    user.getEmail(),
                    user.getRole(),
                    studentNumber,
                    employeeNumber
            );
        }

        throw new UnauthenticatedException();
    }

    @Override
    @Transactional
    public User registerStudent(StudentRegistrationRequest request) {

        School school = schoolService.get(request.schoolId());
        Program program = programService.get(request.programId());

        User user = new User();
        user.setLastname(request.lastname());
        user.setFirstname(request.firstname());
        user.setMiddlename(request.middlename());
        user.setEmail(request.email().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.ROLE_STUDENT);
        userRepository.save(user);

        Student student = new Student();
        student.setStudentNumber(request.studentNumber());
        student.setSchool(school);
        student.setProgram(program);
        student.setUser(user);
        studentRepository.save(student);

        return user;
    }

    @Override
    public User registerEmployee(EmployeeRegistrationRequest request) {

        User user = new User();
        user.setLastname(request.lastname());
        user.setFirstname(request.firstname());
        user.setMiddlename(request.middlename());
        user.setEmail(request.email().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.ROLE_EMPLOYEE);
        userRepository.save(user);

        Employee employee = new Employee();
        employee.setEmployeeNumber(request.employeeNumber());
        employee.setUser(user);
        employeeRepository.save(employee);

        return user;
    }

}
