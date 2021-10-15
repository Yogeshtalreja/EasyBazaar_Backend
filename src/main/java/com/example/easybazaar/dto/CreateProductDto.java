package com.example.easybazaar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {

    private Long productId;
    private Long sellerId;
    private String name;
    private String description;
    private String productCode;
    private String category;
    private Long regularPrice;
    private Long availableQuantity;
    private List<Long> availableColors;

}
