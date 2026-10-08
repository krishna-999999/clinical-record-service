package com.westgate.clinicalrecordservice.controller;

import com.westgate.clinicalrecordservice.dto.ClinicalRecordRequest;
import com.westgate.clinicalrecordservice.dto.ClinicalRecordResponse;
import com.westgate.clinicalrecordservice.service.ClinicalRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinical-records")
public class ClinicalRecordController {

	private final ClinicalRecordService clinicalRecordService;

	public ClinicalRecordController(ClinicalRecordService clinicalRecordService) {

		this.clinicalRecordService = clinicalRecordService;
	}

	@PostMapping
	public ResponseEntity<ClinicalRecordResponse> createRecord(@Valid @RequestBody ClinicalRecordRequest request,
			@RequestHeader("X-User-Id") Long userId, @RequestHeader("X-User-Role") String role) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(clinicalRecordService.createRecord(request, userId, role));
	}

	@GetMapping
	public ResponseEntity<List<ClinicalRecordResponse>> getAllRecords(@RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Role") String role) {

		return ResponseEntity.ok(clinicalRecordService.getAllRecords(userId, role));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClinicalRecordResponse> getRecordById(@PathVariable Long id,
			@RequestHeader("X-User-Id") Long userId, @RequestHeader("X-User-Role") String role) {

		return ResponseEntity.ok(clinicalRecordService.getRecordById(id, userId, role));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClinicalRecordResponse> updateRecord(@PathVariable Long id,
			@Valid @RequestBody ClinicalRecordRequest request, @RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Role") String role) {

		return ResponseEntity.ok(clinicalRecordService.updateRecord(id, request, userId, role));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> archiveRecord(@PathVariable Long id, @RequestHeader("X-User-Id") Long userId,
			@RequestHeader("X-User-Role") String role) {

		clinicalRecordService.archiveRecord(id, userId, role);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/patient/{patientId}")
	public ResponseEntity<List<ClinicalRecordResponse>> getRecordsByPatient(@PathVariable Long patientId,
			@RequestHeader("X-User-Id") Long userId, @RequestHeader("X-User-Role") String role) {

		return ResponseEntity.ok(clinicalRecordService.getRecordsByPatient(patientId, userId, role));
	}
}