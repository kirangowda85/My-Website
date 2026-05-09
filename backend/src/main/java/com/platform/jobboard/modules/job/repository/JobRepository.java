package com.platform.jobboard.modules.job.repository;

import com.platform.jobboard.modules.job.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
    Page<Job> findAllByStatusAndShiftDateAfter(Job.JobStatus status, java.time.LocalDate date, Pageable pageable);
}
