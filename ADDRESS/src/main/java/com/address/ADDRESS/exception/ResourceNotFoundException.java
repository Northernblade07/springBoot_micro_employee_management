package com.address.ADDRESS.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    private String message;
    private HttpStatus status;

    public ResourceNotFoundException(String message){
        this.message = message;
        this.status = HttpStatus.NOT_FOUND;
    }


}
