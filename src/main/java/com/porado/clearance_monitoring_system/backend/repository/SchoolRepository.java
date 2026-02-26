package com.porado.clearance_monitoring_system.backend.repository;

import com.porado.clearance_monitoring_system.backend.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
