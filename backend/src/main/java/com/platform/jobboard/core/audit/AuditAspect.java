package com.platform.jobboard.core.audit;

import com.platform.jobboard.modules.moderation.entity.AdminAuditLog;
import com.platform.jobboard.modules.moderation.repository.AdminAuditLogRepository;
import com.platform.jobboard.modules.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AdminAuditLogRepository auditLogRepository;

    @AfterReturning(pointcut = "@annotation(auditLog)", returning = "result")
    public void logAction(JoinPoint joinPoint, AuditLog auditLog, Object result) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (principal instanceof User user) {
            AdminAuditLog log = AdminAuditLog.builder()
                    .adminId(user.getId())
                    .action(auditLog.action())
                    .details("Method: " + joinPoint.getSignature().getName())
                    .build();
            auditLogRepository.save(log);
        }
    }
}
