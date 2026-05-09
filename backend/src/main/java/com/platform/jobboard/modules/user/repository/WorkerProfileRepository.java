package com.platform.jobboard.modules.user.repository;

import com.platform.jobboard.modules.user.entity.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface WorkerProfileRepository extends JpaRepository<WorkerProfile, UUID> {
    // Additional query methods if needed
}
