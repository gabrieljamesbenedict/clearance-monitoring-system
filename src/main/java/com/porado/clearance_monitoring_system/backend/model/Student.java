package com.porado.clearance_monitoring_system.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    private Long studentId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column(nullable = false, unique = true)
    private String studentNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "schoolId", nullable = false)
    private School school;

    @ManyToOne(optional = false)
    @JoinColumn(name = "programId", nullable = false)
    private Program program;

}
