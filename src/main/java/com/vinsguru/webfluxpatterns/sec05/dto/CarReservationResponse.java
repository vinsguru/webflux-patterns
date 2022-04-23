package com.vinsguru.webfluxpatterns.sec05.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CarReservationResponse {

    private UUID reservationId;
    private String city;
    private LocalDate pickup;
    private LocalDate drop;
    private String category;
    private Integer price;

}
