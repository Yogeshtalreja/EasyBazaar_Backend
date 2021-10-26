package com.example.easybazaar.dto;

import com.example.easybazaar.enums.UserType;
import com.example.easybazaar.model.BankAccountDetails;
import com.example.easybazaar.model.City;
import com.example.easybazaar.model.ProductVariant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class  SellerDto {
    private Long id;
    private String name;
    private String companyName;
    private Long rating;
    private LocalDate registrationDate;
    private String imagePath;
    private String imageName;
    private String address;
    private String email;
    private String password;
    private Boolean isActive;
    private Boolean isVerified;
    private String cnic;
    private String contactNumber;
    private UserType userType;
    private Long cityId;
    private List<ProductVariant> purchaseProducts;
    private List<BankAccountDetails> bankAccountDetails;

}
