package com.example.easybazaar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tbl_order_details")
public class OrderDetails {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "sub_total")
    private Long subTotal;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    @JsonIgnore
    private ProductVariant product;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    @JsonIgnore
    private Order order;
}
