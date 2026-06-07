package com.apiGateway.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<com.apiGateway.exception.ErrorResponse> handleBadRequestException(BadRequestException ex){
        com.apiGateway.exception.ErrorResponse errorResponse = new com.apiGateway.exception.ErrorResponse(ex.getMessage() , ex.getStatus());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

}

