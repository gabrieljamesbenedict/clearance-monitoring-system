package com.porado.clearance_monitoring_system.backend.serviceImpl;

import com.porado.clearance_monitoring_system.backend.exception.ProgramNotFoundException;
import com.porado.clearance_monitoring_system.backend.model.Program;
import com.porado.clearance_monitoring_system.backend.repository.ProgramRepository;
import com.porado.clearance_monitoring_system.backend.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository schoolRepository;

    @Override
    public Program get(Long id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Program not found with Id=" + id));
    }
}
