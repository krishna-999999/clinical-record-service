package com.westgate.clinicalrecordservice.exception;

public class AccessDeniedException
        extends RuntimeException {

    public AccessDeniedException(String message) {
        super(message);
    }
}