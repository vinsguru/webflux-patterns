package com.vinsguru.webfluxpatterns.sec02.service;

import com.vinsguru.webfluxpatterns.sec02.client.DeltaClient;
import com.vinsguru.webfluxpatterns.sec02.client.FrontierClient;
import com.vinsguru.webfluxpatterns.sec02.client.JetBlueClient;
import com.vinsguru.webfluxpatterns.sec02.dto.FlightResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class FlightSearchService {

    @Autowired
    private DeltaClient deltaClient;

    @Autowired
    private FrontierClient frontierClient;

    @Autowired
    private JetBlueClient jetBlueClient;

    public Flux<FlightResult> getFlights(String from, String to){
        return Flux.merge(
                this.deltaClient.getFlights(from, to),
                this.frontierClient.getFlights(from, to),
                this.jetBlueClient.getFlights(from, to)
        )
        .take(Duration.ofSeconds(3));
    }

}
