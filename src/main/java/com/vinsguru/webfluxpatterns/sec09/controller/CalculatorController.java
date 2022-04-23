package com.vinsguru.webfluxpatterns.sec09.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("sec09")
public class CalculatorController {

    // CPU intensive
    // 5 requests / 20 seconds
    @GetMapping("calculator/{input}")
    @RateLimiter(name = "calculator-service", fallbackMethod = "fallback")
    public Mono<ResponseEntity<Integer>> doubleInput(@PathVariable Integer input){
        return Mono.fromSupplier(() -> input * 2)
                .map(ResponseEntity::ok);
    }

    public Mono<ResponseEntity<String>> fallback(Integer input, Throwable ex){
        return Mono.just(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ex.getMessage()));
    }

}
