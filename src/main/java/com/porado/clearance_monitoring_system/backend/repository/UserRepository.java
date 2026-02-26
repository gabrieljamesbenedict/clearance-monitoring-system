package com.porado.clearance_monitoring_system.backend.repository;

import com.porado.clearance_monitoring_system.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
