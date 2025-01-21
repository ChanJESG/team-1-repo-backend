package com.example.rootsquad.backend.exception;

public class MessageNotReadableException extends RuntimeException {
    public MessageNotReadableException() {
        super("Input is invalid. Please try again.");
    }
}
