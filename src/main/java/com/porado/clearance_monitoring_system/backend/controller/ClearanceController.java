/**
 * Exposes REST API for Clearance.
 * Communicates with ClearanceService.
 */

package com.porado.clearance_monitoring_system.backend.controller;

import com.porado.clearance_monitoring_system.backend.model.Clearance;
import com.porado.clearance_monitoring_system.backend.service.ClearanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Changed from @Controller to @RestController
@RequestMapping("/clearances")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080", "http://127.0.0.1:8080"})
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
    public ResponseEntity<Void> deleteClearance(@PathVariable Long id) {
        clearanceService.deleteClearance(id);
        return ResponseEntity.noContent().build();
    }

}