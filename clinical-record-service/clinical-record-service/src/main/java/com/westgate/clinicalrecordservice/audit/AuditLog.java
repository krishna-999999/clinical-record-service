package com.westgate.clinicalrecordservice.audit;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String action;

	@Column(nullable = false)
	private String entityName;

	private Long entityId;

	private Long userId;

	@Column(nullable = false)
	private LocalDateTime timestamp;

	public AuditLog() {
	}

	public AuditLog(String action, String entityName, Long entityId, Long userId) {

		this.action = action;
		this.entityName = entityName;
		this.entityId = entityId;
		this.userId = userId;
		this.timestamp = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public String getAction() {
		return action;
	}

	public String getEntityName() {
		return entityName;
	}

	public Long getEntityId() {
		return entityId;
	}

	public Long getUserId() {
		return userId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}