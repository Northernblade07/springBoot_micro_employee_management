package com.apiGateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.function.Predicate;

@Component
public class Validator {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public static final List<String> endpoints = List.of(
            "/api/v1/auth/register",
            "/api/v1/auth/generateToken",
            "/validate-token/**"
    );

    public Predicate<ServerHttpRequest> predicate = serverHttpRequest -> {
        String path = serverHttpRequest.getURI().getPath();
        return endpoints.stream().noneMatch(endPoint -> antPathMatcher.match(endPoint, path));
    };

}
