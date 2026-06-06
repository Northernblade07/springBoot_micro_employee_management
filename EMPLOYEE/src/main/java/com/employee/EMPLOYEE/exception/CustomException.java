package com.employee.EMPLOYEE.exception;

import org.springframework.http.HttpStatus;


public class CustomException extends RuntimeException{

    private HttpStatus status;

    public CustomException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }

    public CustomException(String  message){
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
