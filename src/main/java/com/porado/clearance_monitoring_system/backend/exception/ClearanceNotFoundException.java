package com.porado.clearance_monitoring_system.backend.exception;

public class ClearanceNotFoundException extends RuntimeException {
    public ClearanceNotFoundException(String message) {
        super(message);
    }
}
