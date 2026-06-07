package com.apiGateway.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException{

    private String message;
    private HttpStatus status;

    public BadRequestException(String message) {
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public BadRequestException(String message , HttpStatus status){
         this.message = message;
         this.status = status;
    }
}
