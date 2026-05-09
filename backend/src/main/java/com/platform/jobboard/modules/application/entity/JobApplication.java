package com.platform.jobboard.modules.application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "job_applications", indexes = {
    @Index(name = "IDX_applications_worker", columnList = "worker_id, status"),
    @Index(name = "IDX_applications_job", columnList = "job_id")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "job_id", nullable = false)
    private UUID jobId;

    @Column(name = "worker_id", nullable = false)
    private UUID workerId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    @CreationTimestamp
    private LocalDateTime appliedAt;

    private LocalDateTime updatedAt;

    public enum ApplicationStatus {
        APPLIED, ACCEPTED, REJECTED, WITHDRAWN, COMPLETED, NO_SHOW
    }
}
