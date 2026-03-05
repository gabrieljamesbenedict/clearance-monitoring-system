package com.porado.clearance_monitoring_system.backend.controller;

import com.porado.clearance_monitoring_system.backend.dto.ClearanceCreationRequest;
import com.porado.clearance_monitoring_system.backend.dto.ClearanceStudentResponse;
import com.porado.clearance_monitoring_system.backend.dto.ClearanceUpdateRequest;
import com.porado.clearance_monitoring_system.backend.model.Clearance;
import com.porado.clearance_monitoring_system.backend.service.ClearanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clearances")
@RequiredArgsConstructor
public class ClearanceController {

    private final ClearanceService clearanceService;

    @GetMapping
    public ResponseEntity<List<ClearanceStudentResponse>> getAllStudentClearances(@RequestParam Long studentId) {
        return ResponseEntity.ok(clearanceService.getAllStudentClearances(studentId));
    }

    @GetMapping("/{clearanceId}")
    public ResponseEntity<ClearanceStudentResponse> getStudentClearance(@RequestParam Long studentId, @PathVariable Long clearanceId) {
        return ResponseEntity.ok(clearanceService.getStudentClearance(studentId, clearanceId));
    }

    @PostMapping
    public ResponseEntity<Clearance> createClearance(@RequestBody ClearanceCreationRequest request) {
        Clearance clearance = clearanceService.createClearance(request);
        URI location = URI.create("/clearances/" + clearance.getClearanceId());
        return ResponseEntity.created(location).body(clearance);
    }

    @PutMapping
    public ResponseEntity<Clearance> updateClearance(@RequestBody ClearanceUpdateRequest request) {
        return ResponseEntity.ok(clearanceService.updateClearance(request));
    }

    @DeleteMapping("/{clearanceId}")
    public void deleteClearance(@PathVariable Long clearanceId) {
        clearanceService.deleteClearance(clearanceId);
    }

}
