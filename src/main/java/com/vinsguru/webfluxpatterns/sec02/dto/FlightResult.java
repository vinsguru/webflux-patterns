package com.vinsguru.webfluxpatterns.sec02.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class FlightResult {

    private String airline;
    private String from;
    private String to;
    private Double price;
    private LocalDate date;

}
