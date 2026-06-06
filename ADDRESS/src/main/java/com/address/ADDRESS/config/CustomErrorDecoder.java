package com.address.ADDRESS.config;

import com.address.ADDRESS.exception.CustomException;
import com.address.ADDRESS.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        try(InputStream is = response.body().asInputStream()){
            ErrorResponse errorResponse = objectMapper.readValue(is , ErrorResponse.class);
            return new CustomException(errorResponse.getMessage() , errorResponse.getStatus());
        }catch (IOException e){
            throw new CustomException("Internal_server_error");
        }
    }
}
