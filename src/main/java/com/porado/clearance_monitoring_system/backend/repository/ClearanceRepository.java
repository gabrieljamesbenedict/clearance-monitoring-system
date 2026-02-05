package com.porado.clearance_monitoring_system.backend.repository;

import com.porado.clearance_monitoring_system.backend.model.Clearance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClearanceRepository extends JpaRepository<Clearance, Long> {
}
