package com.platform.jobboard.modules.user.repository;

import com.platform.jobboard.modules.user.entity.EmployerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EmployerProfileRepository extends JpaRepository<EmployerProfile, UUID> {
    // Additional query methods if needed
}
