package com.porado.clearance_monitoring_system.backend.service;

import com.porado.clearance_monitoring_system.backend.model.Clearance;

import java.util.List;
import java.util.Optional;

public interface ClearanceService {
    List<Clearance> getAllClearances();
    Clearance getClearanceById(Long id);
    Clearance createClearance(Clearance clearance);
    Clearance updateClearance(Long id, Clearance clearance);
    void deleteClearance(Long id);
}
