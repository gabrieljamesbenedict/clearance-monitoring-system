package com.porado.clearance_monitoring_system.backend.repository;

import com.porado.clearance_monitoring_system.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Boolean existsByStudentNumber(String studentNumber);
}
