package com.example.easybazaar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipperHistoryDto {

    public ShipperHistoryDto(LocalDate orderDate, String orderId, String orderAddress) {
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.orderAddress = orderAddress;
    }

    private LocalDate orderDate;
    private String orderId;
    private String orderAddress;
    private Double amount;
}
