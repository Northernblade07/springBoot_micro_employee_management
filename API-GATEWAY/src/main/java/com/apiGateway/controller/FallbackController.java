package com.apiGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @GetMapping("/employeeServiceFallback")
    public Mono<String> employeeServiceFallback() {
        return Mono.just("Employee Service is down. Please try again later.");
    }

    @GetMapping("/addressServiceFallback")
    public Mono<String> addressServiceFallback() {
        return Mono.just("Address Service is down. Please try again later.");
    }
}
