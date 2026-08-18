package org.example.interviewservice.exception;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException(int id) {
        super("Application with id " + id + " not found");
    }
    public ApplicationNotFoundException() {
        super("No Applications found");
    }
}