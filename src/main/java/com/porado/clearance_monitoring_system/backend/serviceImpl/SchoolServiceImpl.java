package com.porado.clearance_monitoring_system.backend.serviceImpl;

import com.porado.clearance_monitoring_system.backend.exception.SchoolNotFoundException;
import com.porado.clearance_monitoring_system.backend.model.School;
import com.porado.clearance_monitoring_system.backend.repository.SchoolRepository;
import com.porado.clearance_monitoring_system.backend.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Override
    public School get(Long id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new SchoolNotFoundException("School not found with Id=" + id));
    }
}
