package com.example.demo_circuit_breaker.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api")
public class ExternalServiceController {


    
    @CircuitBreaker(name = "externalServiceCB", fallbackMethod = "fallback")
    @GetMapping("/external")
    public String callExternalService() {
        // Simular un fallo en el servicio externo
        if (new Random().nextInt(10) < 7) {
            throw new RuntimeException("Service is unavailable");
        }
        return "External service response";
    }

    public String fallback(Throwable t) {
        return "Fallback response: Service is temporarily unavailable " + t.getMessage();
    }
}
