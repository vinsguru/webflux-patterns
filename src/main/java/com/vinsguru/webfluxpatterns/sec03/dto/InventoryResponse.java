package com.vinsguru.webfluxpatterns.sec03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class InventoryResponse {

    private Integer productId;
    private Integer quantity;
    private Integer remainingQuantity;
    private Status status;

}
