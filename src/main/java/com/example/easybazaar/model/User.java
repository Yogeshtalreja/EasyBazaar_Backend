package com.example.easybazaar.model;


import com.example.easybazaar.enums.GenderEnum;
import com.example.easybazaar.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tbl_user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "seller_company_name")
    private String companyName;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "address")
    private String address;

    @Column(name = "email" , unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "isVerified")
    private Boolean isVerified;

    @Column(name = "isLive")
    private Boolean isLive;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "cnic", unique = true)
    private String cnic;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(name = "user_type")
    private String userType;

    @ManyToOne
    private City city;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<ProductVariant> purchaseProducts;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<BankAccountDetails> bankAccountDetails;






}
