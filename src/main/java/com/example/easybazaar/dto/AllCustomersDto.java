package com.example.easybazaar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AllCustomersDto {
    private Long id;
    private String name;
    private LocalDate registrationDate;
    private String imagePath;
    private String imageName;
    private Boolean isActive;
    private String address;
    private String email;
    private Boolean isVerified;
    private String contactNumber;
    private String cnic;
    private LocalDate dob;
    private String gender;
    private String city;

    public AllCustomersDto(Long id, String name, LocalDate registrationDate, String imagePath, String imageName, Boolean isActive, String address, String email, Boolean isVerified, String contactNumber, String cnic, LocalDate dob, String gender, String city) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
        this.imagePath = imagePath;
        this.imageName = imageName;
        this.isActive = isActive;
        this.address = address;
        this.email = email;
        this.isVerified = isVerified;
        this.contactNumber = contactNumber;
        this.cnic = cnic;
        this.dob = dob;
        this.gender = gender;
        this.city = city;
    }
}
