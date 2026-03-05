package com.porado.clearance_monitoring_system.backend.model;

import com.porado.clearance_monitoring_system.backend.util.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "clearances")
@Getter
@Setter
@NoArgsConstructor
public class Clearance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clearanceId;

    @Column(nullable = false)
    private String purpose;


    @Column(nullable = false)
    private String academicYear;


    @Column(nullable = false)
    private String semester;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = true)
    private Instant deletedAt;

}
