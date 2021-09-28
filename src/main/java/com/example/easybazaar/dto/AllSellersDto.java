package com.example.easybazaar.dto;

import com.example.easybazaar.enums.UserType;
import com.example.easybazaar.model.City;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AllSellersDto {
    private Long id;
    private String name;
    private String companyName;
    private Long rating;
    private String imagePath;
    private String imageName;
    private String address;
    private String email;
    private Boolean isActive;
    private String contactNumber;
    private String city;

    public AllSellersDto(Long id, String name, String companyName, Long rating, String imagePath, String imageName, String address, String email, Boolean isActive, String contactNumber, String city) {
        this.id = id;
        this.name = name;
        this.companyName = companyName;
        this.rating = rating;
        this.imagePath = imagePath;
        this.imageName = imageName;
        this.address = address;
        this.email = email;
        this.isActive = isActive;
        this.contactNumber = contactNumber;
        this.city = city;
    }
}
