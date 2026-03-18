package com.porado.clearance_monitoring_system.backend.controller;

import com.porado.clearance_monitoring_system.backend.dto.ClearanceCreationRequest;
import com.porado.clearance_monitoring_system.backend.dto.ClearanceStudentResponse;
import com.porado.clearance_monitoring_system.backend.dto.ClearanceUpdateRequest;
import com.porado.clearance_monitoring_system.backend.model.Clearance;
import com.porado.clearance_monitoring_system.backend.service.ClearanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.porado.clearance_monitoring_system.backend.dto.ClearanceAdminResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clearances")
@RequiredArgsConstructor
public class ClearanceController {

    private final ClearanceService clearanceService;

    @GetMapping
    public ResponseEntity<List<ClearanceStudentResponse>> getAllStudentClearances(@RequestParam("studentId") Long studentId) {
        return ResponseEntity.ok(clearanceService.getAllStudentClearances(studentId));
    }

    @GetMapping("/{clearanceId}")
    public ResponseEntity<ClearanceStudentResponse> getStudentClearance(@RequestParam("studentId") Long studentId, @PathVariable("clearanceId") Long clearanceId) {
        return ResponseEntity.ok(clearanceService.getStudentClearance(studentId, clearanceId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClearanceAdminResponse>> getAllClearances() {
        return ResponseEntity.ok(clearanceService.getAllClearances());
    }

    @PostMapping
    public ResponseEntity<ClearanceStudentResponse> createClearance(@RequestBody ClearanceCreationRequest request) {
        Clearance clearance = clearanceService.createClearance(request);
        URI location = URI.create("/clearances/" + clearance.getClearanceId());
        return ResponseEntity.created(location).body(ClearanceStudentResponse.toStudentClearance(clearance));
    }

    @PutMapping
    public ResponseEntity<ClearanceStudentResponse> updateClearance(@RequestBody ClearanceUpdateRequest request) {
        Clearance clearance = clearanceService.updateClearance(request);
        return ResponseEntity.ok(ClearanceStudentResponse.toStudentClearance(clearance));
    }

    @DeleteMapping("/{clearanceId}")
    public void deleteClearance(@PathVariable("clearanceId") Long clearanceId) {
        clearanceService.deleteClearance(clearanceId);
    }

}