package com.example.easybazaar.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

}
