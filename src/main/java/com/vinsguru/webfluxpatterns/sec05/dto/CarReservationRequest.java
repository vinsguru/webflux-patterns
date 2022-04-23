package com.vinsguru.webfluxpatterns.sec05.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CarReservationRequest {

    private String city;
    private LocalDate pickup;
    private LocalDate drop;
    private String category;

}
