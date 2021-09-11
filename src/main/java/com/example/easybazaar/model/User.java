package com.example.easybazaar.model;

import com.example.easybazaar.enums.GenderEnum;
import com.example.easybazaar.enums.UserType;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @Column(name = "tbl_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tbl_name")
    private String name;

    @Column(name = "tbl_rating")
    private Long rating;

    @Column(name = "tbl_registration_date")
    private LocalDate registrationDate;

    @Column(name = "tbl_image_path")
    private String imagePath;

    @Column(name = "tbl_image_name")
    private String imageName;

    @Column(name = "tbl_isActive")
    private Boolean isActive;

    @Column(name = "tbl_address")
    private String address;

    @Column(name = "tbl_email" , unique = true)
    private String email;

    @Column(name = "tbl_password")
    private String password;

    @Column(name = "tbl_isVerified")
    private Boolean isVerified;

    @Column(name = "tbl_isLive")
    private Boolean isLive;

    @Column(name = "tbl_contact_number")
    private String contactNumber;

    @Column(name = "tbl_cnic", unique = true)
    private String cnic;

    @Column(name = "tbl_dob")
    private LocalDate dob;

    @Column(name = "tbl_gender")
    private GenderEnum gender;

    @Column(name = "tbl_user_type")
    private UserType userType;


}
