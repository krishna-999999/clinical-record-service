package com.westgate.clinicalrecordservice.service;

import com.westgate.clinicalrecordservice.dto.ClinicalRecordRequest;
import com.westgate.clinicalrecordservice.dto.ClinicalRecordResponse;

import java.util.List;

public interface ClinicalRecordService {

	ClinicalRecordResponse createRecord(ClinicalRecordRequest request, Long userId, String role);

	List<ClinicalRecordResponse> getAllRecords(Long userId, String role);

	ClinicalRecordResponse getRecordById(Long id, Long userId, String role);

	ClinicalRecordResponse updateRecord(Long id, ClinicalRecordRequest request, Long userId, String role);

	void archiveRecord(Long id, Long userId, String role);

	List<ClinicalRecordResponse> getRecordsByPatient(Long patientId, Long userId, String role);
}