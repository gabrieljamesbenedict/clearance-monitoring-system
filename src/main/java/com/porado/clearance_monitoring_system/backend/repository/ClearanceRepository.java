package com.porado.clearance_monitoring_system.backend.repository;

import com.porado.clearance_monitoring_system.backend.model.Clearance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClearanceRepository extends JpaRepository<Clearance, Long> {
    List<Clearance> findAllByUser_UserId(Long studentId);
    Optional<Clearance> findByUser_UserId(Long studentId, Long clearanceId);
}
