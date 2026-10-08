package com.westgate.clinicalrecordservice.dto;

import com.westgate.clinicalrecordservice.entity.RecordStatus;
import com.westgate.clinicalrecordservice.entity.RecordType;

import java.time.LocalDateTime;

public class ClinicalRecordResponse {

	private Long id;
	private Long patientId;
	private Long providerId;
	private RecordType recordType;
	private String description;
	private String diagnosis;
	private String treatment;
	private String notes;
	private RecordStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public ClinicalRecordResponse(Long id, Long patientId, Long providerId, RecordType recordType, String description,
			String diagnosis, String treatment, String notes, RecordStatus status, LocalDateTime createdAt,
			LocalDateTime updatedAt) {

		this.id = id;
		this.patientId = patientId;
		this.providerId = providerId;
		this.recordType = recordType;
		this.description = description;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.notes = notes;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public RecordType getRecordType() {
		return recordType;
	}

	public String getDescription() {
		return description;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public String getNotes() {
		return notes;
	}

	public RecordStatus getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}