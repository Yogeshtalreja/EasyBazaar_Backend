package com.example.easybazaar.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddProductDto {

    private Long sellerId;
    private String name;
    private String description;
    private Long availableQuantity;
    private Long sellPrice;
    private LocalDate expiryDate;
    private Long weight;
    private String company;
    private List<Long> availableSizes;
    private List<Long> availableColors;

}
