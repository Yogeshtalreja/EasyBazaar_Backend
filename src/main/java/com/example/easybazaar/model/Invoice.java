package com.example.easybazaar.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tbl_invoice")
public class Invoice {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "invoice_number")
    private String invoiceNumber;


    @OneToOne
    private Transaction transaction;

    @OneToMany(targetEntity = Reviews.class)
    private List<Reviews> reviews;



}
