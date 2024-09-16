package com.example.prueba.exception;

import com.example.prueba.exception.resource.ErrorMessage;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleClienteNotFoundException(ClienteNotFoundException exception) {
        logger.error("Cliente not found: {}", exception.getMessage(), exception);

        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException ex) {
        logger.error("Constraint violation: {}", ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String propertyName = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(propertyName, message);
        });

        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errors(errors)
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // Optional: Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex) {
        logger.error("Unexpected error: {}", ex.getMessage(), ex);

        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("An unexpected error occurred.")
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

