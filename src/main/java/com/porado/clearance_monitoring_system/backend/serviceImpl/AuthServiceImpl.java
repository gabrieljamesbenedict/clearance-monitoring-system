package com.porado.clearance_monitoring_system.backend.serviceImpl;

import com.porado.clearance_monitoring_system.backend.auth.MapuanUserDetails;
import com.porado.clearance_monitoring_system.backend.exception.UnauthenticatedException;
import com.porado.clearance_monitoring_system.backend.model.Employee;
import com.porado.clearance_monitoring_system.backend.model.Student;
import com.porado.clearance_monitoring_system.backend.model.User;
import com.porado.clearance_monitoring_system.backend.repository.EmployeeRepository;
import com.porado.clearance_monitoring_system.backend.repository.StudentRepository;
import com.porado.clearance_monitoring_system.backend.repository.UserRepository;
import com.porado.clearance_monitoring_system.backend.service.AuthService;
import com.porado.clearance_monitoring_system.backend.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthenticatedException();
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof MapuanUserDetails details) {
            return details.getUser();
        }

        throw new UnauthenticatedException();
    }

    @Override
    @Transactional
    public User registerStudent(String email, String rawPassword, Student student) {
        User user = new User();
        user.setEmail(email.toLowerCase());
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(Role.ROLE_STUDENT);
        userRepository.save(user);

        student.setUser(user);
        studentRepository.save(student);

        return user;
    }

    @Override
    public User registerEmployee(String email, String rawPassword, Employee employee) {
        User user = new User();
        user.setEmail(email.toLowerCase());
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(Role.ROLE_EMPLOYEE);

        userRepository.save(user);

        employee.setUser(user);
        employeeRepository.save(employee);

        return user;
    }

}
