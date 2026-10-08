package com.westgate.clinicalrecordservice.repository;

import com.westgate.clinicalrecordservice.entity.ClinicalRecord;
import com.westgate.clinicalrecordservice.entity.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicalRecordRepository extends JpaRepository<ClinicalRecord, Long> {

	List<ClinicalRecord> findByPatientId(Long patientId);

	List<ClinicalRecord> findByPatientIdAndStatus(Long patientId, RecordStatus status);
}