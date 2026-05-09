package com.platform.jobboard.modules.moderation.repository;

import com.platform.jobboard.modules.moderation.entity.AdminAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AdminAuditLogRepository extends JpaRepository<AdminAuditLog, UUID> {
}
