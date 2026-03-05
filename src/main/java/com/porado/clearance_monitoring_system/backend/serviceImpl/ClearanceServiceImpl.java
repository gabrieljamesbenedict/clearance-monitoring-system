package com.porado.clearance_monitoring_system.backend.serviceImpl;

import com.porado.clearance_monitoring_system.backend.dto.ClearanceCreationRequest;
import com.porado.clearance_monitoring_system.backend.dto.ClearanceStudentResponse;
import com.porado.clearance_monitoring_system.backend.dto.ClearanceUpdateRequest;
import com.porado.clearance_monitoring_system.backend.exception.ClearanceNotFoundException;
import com.porado.clearance_monitoring_system.backend.exception.StudentNotFoundException;
import com.porado.clearance_monitoring_system.backend.model.Clearance;
import com.porado.clearance_monitoring_system.backend.model.User;
import com.porado.clearance_monitoring_system.backend.repository.ClearanceRepository;
import com.porado.clearance_monitoring_system.backend.repository.StudentRepository;
import com.porado.clearance_monitoring_system.backend.repository.UserRepository;
import com.porado.clearance_monitoring_system.backend.service.ClearanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClearanceServiceImpl implements ClearanceService {

    private final ClearanceRepository clearanceRepository;
    private final UserRepository userRepository;

    @Override
    public List<ClearanceStudentResponse> getAllStudentClearance(Long studentId) {
        return clearanceRepository
                .findAllByUser_UserId(studentId)
                .stream()
                .map(ClearanceStudentResponse::toStudentClearance)
                .toList();
    }

    @Override
    public ClearanceStudentResponse getStudentClearance(Long studentId, Long clearanceId) {
        Clearance clearance = clearanceRepository
                .findByUser_UserId(studentId, clearanceId)
                .orElseThrow(() -> new ClearanceNotFoundException("Clearance not found with Id=" + clearanceId));
        return ClearanceStudentResponse.toStudentClearance(clearance);
    }

    @Override
    public Clearance createClearance(ClearanceCreationRequest request) {

        User user = userRepository.findById(request.studentId()).orElseThrow(() -> new StudentNotFoundException("Student not found with Id=" + request.studentId()));

        Clearance clearance = new Clearance();
        clearance.setUser(user);
        clearance.setPurpose(request.purpose());
        clearance.setAcademicYear(request.academicYear());
        clearance.setSemester(request.semester());

        return clearanceRepository.save(clearance);
    }

    @Override
    public Clearance updateClearance(ClearanceUpdateRequest request) {

        Clearance clearance = clearanceRepository.findById(request.clearanceId()).orElseThrow(() -> new ClearanceNotFoundException("Clearance not found with Id=" + request.clearanceId()));

        clearance.setPurpose(request.purpose());
        clearance.setAcademicYear(request.academicYear());
        clearance.setSemester(request.semester());

        return clearanceRepository.save(clearance);
    }

    @Override
    public void deleteClearance(Long clearanceId) {
        Clearance clearance = clearanceRepository.findById(clearanceId).orElseThrow(() -> new ClearanceNotFoundException("Clearance not found with Id=" + clearanceId));
        clearance.setDeletedAt(Instant.now());
        clearanceRepository.save(clearance);
    }
}
