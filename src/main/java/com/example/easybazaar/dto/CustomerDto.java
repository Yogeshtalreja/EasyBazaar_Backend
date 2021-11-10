package com.example.easybazaar.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDto {
    private Long id;
    private String name;
    private LocalDate registrationDate;
    private String imagePath;
    private String imageName;
    private Boolean isActive;
    private String address;
    private String email;
    private String password;
    private Boolean isVerified;
    private String contactNumber;
    private String cnic;
    private LocalDate dob;
    private String gender;
    private Long cityId;

}
