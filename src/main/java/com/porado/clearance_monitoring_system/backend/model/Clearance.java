package com.porado.clearance_monitoring_system.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@Entity
@Table(name = "clearances")
public class Clearance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clearanceId;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private Integer studentNumber;

    @Column(nullable = false)
    private String studentProgram;

    @Column(nullable = false)
    private String purposeOfClearance;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant date;

}
