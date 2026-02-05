package com.porado.clearance_monitoring_system.backend.serviceImpl;

import com.porado.clearance_monitoring_system.backend.model.Clearance;
import com.porado.clearance_monitoring_system.backend.repository.ClearanceRepository;
import com.porado.clearance_monitoring_system.backend.service.ClearanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClearanceServiceImpl implements ClearanceService {

    private final ClearanceRepository clearanceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Clearance> getAllClearances() {
        return clearanceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Clearance getClearanceById(Long id) {
        return clearanceRepository.findById(id).orElseThrow(() -> new RuntimeException("No Clearance with Id=" + id));
    }

    @Override
    public Clearance createClearance(Clearance clearance) {
        if (clearance.getClearanceId() != null) throw new IllegalArgumentException("Clearance must not have Id");
        return clearanceRepository.save(clearance);
    }

    @Override
    public Clearance updateClearance(Long id, Clearance clearance) {
        Clearance oldClearance = getClearanceById(id);
        BeanUtils.copyProperties(clearance, oldClearance, "clearanceId");
        return clearanceRepository.save(oldClearance);
    }

    @Override
    public void deleteClearance(Long id) {
        Clearance clearance = getClearanceById(id);
        clearanceRepository.delete(clearance);
    }
}
