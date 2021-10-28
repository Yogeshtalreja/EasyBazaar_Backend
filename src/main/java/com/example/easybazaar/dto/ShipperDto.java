package com.example.easybazaar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipperDto {

    private Long id;
    private String name;
    private String address;
    private String email;
    private String password;
    private String contactNumber;
    private String cnic;
    private LocalDate dob;
    private Long cityId;

}
