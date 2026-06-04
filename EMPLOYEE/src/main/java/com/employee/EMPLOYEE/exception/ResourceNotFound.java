package com.employee.EMPLOYEE.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFound extends RuntimeException{

    private String message;
    private HttpStatus status;

    public ResourceNotFound(String message) {
        this.message = message;
        this.status = HttpStatus.NOT_FOUND;
    }


}
