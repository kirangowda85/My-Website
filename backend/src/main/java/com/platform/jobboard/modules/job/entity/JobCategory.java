package com.platform.jobboard.modules.job.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_categories")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JobCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    private String iconUrl;
    private boolean active = true;
}
