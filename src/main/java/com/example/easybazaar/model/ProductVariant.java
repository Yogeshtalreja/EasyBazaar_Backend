package com.example.easybazaar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.Set;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tbl_product")
public class ProductVariant {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "available_quantity")
    private Long availableQuantity;

    @Column(name = "sell_price")
    private Long sellPrice;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "seller_id")
    private Long sellerId;


    @Column(name = "company_name")
    private String company;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Size> availableSizes;

    @Column(name = "colors")
    @ManyToMany
    private Set<Color> availableColors;

    @ManyToOne
    @JoinColumn(name = "wear_id",referencedColumnName = "id")
    @JsonIgnore
    private WearSubCategoryAttributes wearSubCategory;

    @ManyToOne
    @JoinColumn(name = "electronics_id",referencedColumnName = "id")
    @JsonIgnore
    private ElectronicSubCategoriesAttributes electronicSubCategory;

    @OneToMany(mappedBy = "product",cascade = {CascadeType.ALL})
    private List<ProductImages> productImages;

    @OneToMany(mappedBy = "product",cascade = {CascadeType.ALL})
    private List<OrderDetails> orderDetails;




}
