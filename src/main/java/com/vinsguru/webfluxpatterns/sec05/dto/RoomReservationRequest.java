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
public class RoomReservationRequest {

    private String city;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String category;

}
