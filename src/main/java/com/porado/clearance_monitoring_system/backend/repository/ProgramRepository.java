package com.porado.clearance_monitoring_system.backend.repository;

import com.porado.clearance_monitoring_system.backend.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}
