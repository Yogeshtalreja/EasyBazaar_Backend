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
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Long sellPrice;
    private String sellerName;
    private String company;
    private List<String> productURLs;

    public ProductDto(Long id, String name, String description, Long sellPrice, String company) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sellPrice = sellPrice;
        this.company = company;
    }
}
