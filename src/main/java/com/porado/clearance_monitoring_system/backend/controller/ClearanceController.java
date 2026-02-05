package com.porado.clearance_monitoring_system.backend.controller;

import com.porado.clearance_monitoring_system.backend.model.Clearance;
import com.porado.clearance_monitoring_system.backend.service.ClearanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clearances")
@RequiredArgsConstructor
public class ClearanceController {

    private final ClearanceService clearanceService;

    @GetMapping
    public ResponseEntity<List<Clearance>> getAllClearances() {
        return ResponseEntity.ok(clearanceService.getAllClearances());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clearance> getClearanceById(@PathVariable Long id) {
        return ResponseEntity.ok(clearanceService.getClearanceById(id));
    }

    @PostMapping
    public ResponseEntity<Clearance> createClearance(@RequestBody Clearance clearance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clearanceService.createClearance(clearance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clearance> updateClearance(@PathVariable Long id, @RequestBody Clearance clearance) {
        return ResponseEntity.ok(clearanceService.updateClearance(id, clearance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Clearance> deleteClearance(@PathVariable Long id) {
        clearanceService.deleteClearance(id);
        return ResponseEntity.noContent().build();
    }

}
