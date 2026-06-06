package com.employee.EMPLOYEE.config;

import com.employee.EMPLOYEE.exception.CustomException;
import com.employee.EMPLOYEE.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
         try(InputStream is = response.body().asInputStream()){
             ErrorResponse errorResponse = objectMapper.readValue(is , ErrorResponse.class);
             return new CustomException(errorResponse.getStatus() , errorResponse.getMessage());
         } catch (IOException e){
             throw new CustomException("intern_server_error");
         }
    }
}
