package com.example.java_test.domain.error;

public class InvalidParameter extends RuntimeException {

    public InvalidParameter(String message) {
        super(message);
    }
}
