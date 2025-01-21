package com.example.rootsquad.backend.exception;

public class PasswordBlankException extends RuntimeException {
    public PasswordBlankException() {
        super("Password cannot be blank.");
    }
}
