package com.pepcus.crud.exceptionhandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice

public class GlobalExceptionHandler {
  // Handling Custom Validation Errors
  @ExceptionHandler(value = { MethodArgumentNotValidException.class })
  public final ResponseEntity<ErrorDetails> customValidationErrorHandling(MethodArgumentNotValidException ex,
      WebRequest request) {
    Map<String, String> errorMap = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((errors) -> {
      String fieldName = ((FieldError) errors).getField();
      String message = errors.getDefaultMessage();
      errorMap.put(fieldName, message);
    });
    ErrorDetails errorObj = new ErrorDetails(new Date(), "Validation Error", errorMap);
    return new ResponseEntity<>(errorObj, HttpStatus.BAD_REQUEST);
  }

  // Handling Custom Validation Errors for out of range id
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ErrorDetails message = new ErrorDetails(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<ErrorDetails>(message, HttpStatus.NOT_FOUND);
  }

  // Handling Global Exception
  // @ExceptionHandler(value = { Exception.class })
  // public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex,
  // WebRequest request) {
  //
  // ErrorDetails errorObj = new ErrorDetails(new Date(), ex.getMessage(),
  // request.getDescription(false));
  // return new ResponseEntity<>(errorObj, new HttpHeaders(),
  // HttpStatus.INTERNAL_SERVER_ERROR);
  // }
}