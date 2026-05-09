package com.platform.jobboard.modules.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "worker_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkerProfile {

    @Id
    private UUID userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String phone;
    private String city;

    @ElementCollection
    @CollectionTable(name = "worker_skills", joinColumns = @JoinColumn(name = "worker_id"))
    @Column(name = "skill")
    private List<String> skills;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "trust_score")
    @Builder.Default
    private Integer trustScore = 100;

    @Builder.Default
    private Integer strikes = 0;

    @Column(name = "overall_rating", precision = 3, scale = 2)
    @Builder.Default
    private BigDecimal overallRating = BigDecimal.ZERO;

    @Column(name = "total_jobs_completed")
    @Builder.Default
    private Integer totalJobsCompleted = 0;
}
