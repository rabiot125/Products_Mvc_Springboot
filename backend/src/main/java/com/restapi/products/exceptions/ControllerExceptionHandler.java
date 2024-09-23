package com.restapi.products.exceptions;

import com.restapi.products.exceptions.errors.ErrorMessage;
import com.restapi.products.exceptions.errors.ErrorType;
import com.restapi.products.exceptions.errors.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;


@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex) {
        logger.error("Resource not found: {}", ex.getMessage());
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(), HttpStatus.NOT_FOUND, ErrorType.DANGER);
        return new ResponseEntity<>(message, message.getStatus());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        ErrorMessage message = new ErrorMessage(errorMessage, HttpStatus.BAD_REQUEST, ErrorType.WARNING);
        return new ResponseEntity<>(message, message.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request) {
        final ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorType.DANGER, ex.getLocalizedMessage(), "error occurred");
        final HttpHeaders headers = new HttpHeaders();
        headers.add("ERROR_TYPE", "Exception");

        return new ResponseEntity<>(message, headers, message.getStatus());
    }
}
