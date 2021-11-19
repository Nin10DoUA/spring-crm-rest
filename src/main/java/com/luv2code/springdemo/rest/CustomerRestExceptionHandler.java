package com.luv2code.springdemo.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    //Add an exception handler for CustomerNotFoundException

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc)  {
        //create CustomerErrorResponse
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse(
                HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
        //return ResponseEntity
        return new ResponseEntity<>(customerErrorResponse, HttpStatus.NOT_FOUND);
    }

    //Add another exception handler ... to catch any exception(catch all)
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleAllException (Exception ex)  {
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(customerErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
