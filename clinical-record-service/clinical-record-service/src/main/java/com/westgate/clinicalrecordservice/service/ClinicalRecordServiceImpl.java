package com.westgate.clinicalrecordservice.service;

import com.westgate.clinicalrecordservice.audit.AuditLog;
import com.westgate.clinicalrecordservice.dto.ClinicalRecordRequest;
import com.westgate.clinicalrecordservice.dto.ClinicalRecordResponse;
import com.westgate.clinicalrecordservice.entity.ClinicalRecord;
import com.westgate.clinicalrecordservice.entity.RecordStatus;
import com.westgate.clinicalrecordservice.exception.AccessDeniedException;
import com.westgate.clinicalrecordservice.exception.ClinicalRecordNotFoundException;
import com.westgate.clinicalrecordservice.repository.AuditLogRepository;
import com.westgate.clinicalrecordservice.repository.ClinicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalRecordServiceImpl implements ClinicalRecordService {

	private final ClinicalRecordRepository recordRepository;
	private final AuditLogRepository auditLogRepository;

	public ClinicalRecordServiceImpl(ClinicalRecordRepository recordRepository, AuditLogRepository auditLogRepository) {

		this.recordRepository = recordRepository;
		this.auditLogRepository = auditLogRepository;
	}

	@Override
	public ClinicalRecordResponse createRecord(ClinicalRecordRequest request, Long userId, String role) {

		checkWriteAccess(role);

		ClinicalRecord record = new ClinicalRecord();

		record.setPatientId(request.getPatientId());
		record.setProviderId(request.getProviderId());
		record.setRecordType(request.getRecordType());
		record.setDescription(request.getDescription());
		record.setDiagnosis(request.getDiagnosis());
		record.setTreatment(request.getTreatment());
		record.setNotes(request.getNotes());
		record.setStatus(RecordStatus.ACTIVE);

		ClinicalRecord saved = recordRepository.save(record);

		saveAuditLog("CREATE", saved.getId(), userId);

		return convertToResponse(saved);
	}

	@Override
	public List<ClinicalRecordResponse> getAllRecords(Long userId, String role) {

		checkReadAccess(role);

		return recordRepository.findAll().stream().map(this::convertToResponse).toList();
	}

	@Override
	public ClinicalRecordResponse getRecordById(Long id, Long userId, String role) {

		checkReadAccess(role);

		ClinicalRecord record = recordRepository.findById(id)
				.orElseThrow(() -> new ClinicalRecordNotFoundException(id));

		return convertToResponse(record);
	}

	@Override
	public ClinicalRecordResponse updateRecord(Long id, ClinicalRecordRequest request, Long userId, String role) {

		checkWriteAccess(role);

		ClinicalRecord record = recordRepository.findById(id)
				.orElseThrow(() -> new ClinicalRecordNotFoundException(id));

		if (record.getStatus() == RecordStatus.ARCHIVED) {
			throw new AccessDeniedException("Archived clinical records cannot be modified");
		}

		record.setPatientId(request.getPatientId());
		record.setProviderId(request.getProviderId());
		record.setRecordType(request.getRecordType());
		record.setDescription(request.getDescription());
		record.setDiagnosis(request.getDiagnosis());
		record.setTreatment(request.getTreatment());
		record.setNotes(request.getNotes());

		ClinicalRecord updated = recordRepository.save(record);

		saveAuditLog("UPDATE", updated.getId(), userId);

		return convertToResponse(updated);
	}

	@Override
	public void archiveRecord(Long id, Long userId, String role) {

		checkWriteAccess(role);

		ClinicalRecord record = recordRepository.findById(id)
				.orElseThrow(() -> new ClinicalRecordNotFoundException(id));

		record.setStatus(RecordStatus.ARCHIVED);

		recordRepository.save(record);

		saveAuditLog("ARCHIVE", record.getId(), userId);
	}

	@Override
	public List<ClinicalRecordResponse> getRecordsByPatient(Long patientId, Long userId, String role) {

		checkReadAccess(role);

		return recordRepository.findByPatientIdAndStatus(patientId, RecordStatus.ACTIVE).stream()
				.map(this::convertToResponse).toList();
	}

	private void checkReadAccess(String role) {

		if (role == null || !(role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("CLINICIAN")
				|| role.equalsIgnoreCase("NURSE"))) {

			throw new AccessDeniedException("You do not have permission to access clinical records");
		}
	}

	private void checkWriteAccess(String role) {

		if (role == null || !(role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("CLINICIAN"))) {

			throw new AccessDeniedException("You do not have permission to modify clinical records");
		}
	}

	private void saveAuditLog(String action, Long entityId, Long userId) {

		AuditLog log = new AuditLog(action, "ClinicalRecord", entityId, userId);

		auditLogRepository.save(log);
	}

	private ClinicalRecordResponse convertToResponse(ClinicalRecord record) {

		return new ClinicalRecordResponse(record.getId(), record.getPatientId(), record.getProviderId(),
				record.getRecordType(), record.getDescription(), record.getDiagnosis(), record.getTreatment(),
				record.getNotes(), record.getStatus(), record.getCreatedAt(), record.getUpdatedAt());
	}
}