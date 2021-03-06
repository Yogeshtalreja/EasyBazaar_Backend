package com.example.easybazaar.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    @JsonIgnore
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
    private String gender;

    @Column(name = "user_type")
    private String userType;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany(cascade = {CascadeType.ALL},targetEntity = ProductVariant.class)
    private List<ProductVariant> purchaseProducts;

    @OneToMany(cascade = {CascadeType.ALL},targetEntity = BankAccountDetails.class)
    private List<BankAccountDetails> bankAccountDetails;

    @OneToMany(mappedBy = "shippedBy" , cascade = {CascadeType.ALL})
    private List<Order> shippedBy;

}
