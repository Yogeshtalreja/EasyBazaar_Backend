package com.example.easybazaar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AllSellerProductsDto {
    private String name;
    private String description;
    private Long availableQuantity;
    private Long sellPrice;
    private Long rating;
    private LocalDate createdAt;
    private LocalDate expiryDate;
    private Long weight;
    private String company;

    public AllSellerProductsDto(String name, String description, Long availableQuantity, Long sellPrice, Long rating, LocalDate createdAt, LocalDate expiryDate, Long weight, String company) {
        this.name = name;
        this.description = description;
        this.availableQuantity = availableQuantity;
        this.sellPrice = sellPrice;
        this.rating = rating;
        this.createdAt = createdAt;
        this.expiryDate = expiryDate;
        this.weight = weight;
        this.company = company;
    }
}

