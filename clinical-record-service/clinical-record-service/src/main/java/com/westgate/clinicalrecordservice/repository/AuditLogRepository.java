package com.westgate.clinicalrecordservice.repository;

import com.westgate.clinicalrecordservice.audit.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}