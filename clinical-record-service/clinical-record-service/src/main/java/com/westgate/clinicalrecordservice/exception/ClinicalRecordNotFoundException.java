package com.westgate.clinicalrecordservice.exception;

public class ClinicalRecordNotFoundException extends RuntimeException {

	public ClinicalRecordNotFoundException(Long id) {
		super("Clinical record not found with id: " + id);
	}
}