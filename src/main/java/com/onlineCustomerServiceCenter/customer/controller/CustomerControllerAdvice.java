package com.onlineCustomerServiceCenter.customer.controller;

import com.onlineCustomerServiceCenter.customer.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomerControllerAdvice {
    @ExceptionHandler(value={CustomerRegisterException.class})
    public ResponseEntity<String>CustomerRegisterExceptionHandler(CustomerRegisterException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={CustomerLoginException.class})
    public ResponseEntity<String>CustomerLoginExceptionHandler(CustomerLoginException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(value={CustomerUpdateException.class})
    public ResponseEntity<String>CustomerUpdateExceptionHandler(CustomerUpdateException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={CustomerDeleteException.class})
    public ResponseEntity<String>CustomerDeleteExceptionHandler(CustomerDeleteException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={CustomerNotFoundException.class})
    public ResponseEntity<String>CustomerNotFoundExceptionHandler(CustomerNotFoundException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}