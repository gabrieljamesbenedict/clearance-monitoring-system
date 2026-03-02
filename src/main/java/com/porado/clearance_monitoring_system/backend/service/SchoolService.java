package com.porado.clearance_monitoring_system.backend.service;

import com.porado.clearance_monitoring_system.backend.model.School;

import java.util.List;

public interface SchoolService {
    List<School> getAll();
    School get(Long id);
}
