package com.onlineCustomerServiceCenter.customer.controller;

import com.onlineCustomerServiceCenter.customer.exceptions.CustomerDeleteException;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerLoginException;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerRegisterException;
import com.onlineCustomerServiceCenter.customer.exceptions.CustomerUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}