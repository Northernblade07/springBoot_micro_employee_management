package com.apiGateway.filter;

import com.apiGateway.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private Validator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (validator.predicate.test(exchange.getRequest())){

                if (exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION)==null || exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION).isEmpty()){
                    throw new BadRequestException("Authorization header missing" , HttpStatus.UNAUTHORIZED);
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                String token = null;
                if (authHeader != null && authHeader.startsWith("Bearer ")){
                    token = authHeader.substring(7);
                }else {
                    throw new BadRequestException(
                            "Invalid Authorization header",
                            HttpStatus.UNAUTHORIZED
                    );
                }

                try {
                    jwtUtil.validToken(token);
                } catch (Exception e){
                    throw new BadRequestException("Invalid token",HttpStatus.UNAUTHORIZED);
                }
            }

            return chain.filter(exchange);
        };
    }

    public static class Config{

    }

}
