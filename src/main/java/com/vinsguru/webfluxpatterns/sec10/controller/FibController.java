package com.vinsguru.webfluxpatterns.sec10.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@RestController
@RequestMapping("sec10")
public class FibController {

    private final Scheduler scheduler = Schedulers.newParallel("fib", 6);

    // 0, 1, 1, 2, 3, 5, 8, 13....

    // CPU intensive
    @GetMapping("fib/{input}")
    public Mono<ResponseEntity<Long>> fib(@PathVariable Long input){
        return Mono.fromSupplier(() -> findFib(input))
                .subscribeOn(scheduler)
                .map(ResponseEntity::ok);
    }

    private Long findFib(Long input){
        if(input < 2)
            return input;
        return findFib(input - 1) + findFib(input - 2);
    }

}
