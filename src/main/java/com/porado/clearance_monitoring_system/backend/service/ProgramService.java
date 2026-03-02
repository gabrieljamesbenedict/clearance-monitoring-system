package com.porado.clearance_monitoring_system.backend.service;

import com.porado.clearance_monitoring_system.backend.model.Program;

import java.util.List;

public interface ProgramService {
    List<Program> getAll();
    Program get(Long id);
}
