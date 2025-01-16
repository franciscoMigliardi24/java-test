package com.example.java_test.infrastructure.controller.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<Object> handleThrowable(final Throwable ex, final WebRequest request)
            throws Exception {
        return super.handleException((Exception) ex, request);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handle(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }
}