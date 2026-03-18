package com.porado.clearance_monitoring_system.backend.service;

import com.porado.clearance_monitoring_system.backend.dto.ClearanceCreationRequest;
import com.porado.clearance_monitoring_system.backend.dto.ClearanceStudentResponse;
import com.porado.clearance_monitoring_system.backend.dto.ClearanceUpdateRequest;
import com.porado.clearance_monitoring_system.backend.model.Clearance;
import com.porado.clearance_monitoring_system.backend.dto.ClearanceAdminResponse;
import java.util.List;

public interface ClearanceService {
    List<ClearanceStudentResponse> getAllStudentClearances(Long studentId);
    ClearanceStudentResponse getStudentClearance(Long studentId, Long clearanceId);
    Clearance createClearance(ClearanceCreationRequest clearance);
    Clearance updateClearance(ClearanceUpdateRequest clearance);
    void deleteClearance(Long clearanceId);
    List<ClearanceAdminResponse> getAllClearances();
}
