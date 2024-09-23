package com.restapi.products.exceptions.errors;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ErrorMessage {
    private HttpStatus status;
    private ErrorType errorType;
    private String message;
    private List<String> errors;
    private LocalDateTime timestamp = OffsetDateTime.now(ZoneId.systemDefault()).toLocalDateTime();


    public ErrorMessage(HttpStatus status, ErrorType errorType, String message, List<String> errors) {
        this.status = status;
        this.errorType = errorType;
        this.message = message;
        this.errors = errors;
        this.timestamp = OffsetDateTime.now(ZoneId.systemDefault()).toLocalDateTime();
    }

    public ErrorMessage(HttpStatus status, ErrorType errorType, String message, String error) {
        this.status = status;
        this.errorType = errorType;
        this.message = message;
        this.errors = Collections.singletonList(error);
        this.timestamp = OffsetDateTime.now(ZoneId.systemDefault()).toLocalDateTime();

    }

    public ErrorMessage(HttpStatus status, ErrorType errorType, List<String> errors) {
        this.status = status;
        this.errorType = errorType;
        this.errors = errors;
        this.timestamp = OffsetDateTime.now(ZoneId.systemDefault()).toLocalDateTime();
    }

    public ErrorMessage(String message, HttpStatus status, ErrorType errorType) {
        this.message = message;
        this.errorType = errorType;
        this.status = status;
        this.timestamp = OffsetDateTime.now(ZoneId.systemDefault()).toLocalDateTime();
    }
}
