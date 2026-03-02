package com.porado.clearance_monitoring_system.backend.controller;

import com.porado.clearance_monitoring_system.backend.model.School;
import com.porado.clearance_monitoring_system.backend.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<School>> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> get(@PathVariable Long id) {
        return ResponseEntity.ok(schoolService.get(id));
    }

}
