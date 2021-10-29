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
@Table(name = "tbl_product_images")
public class ProductImages {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    @JsonIgnore
    private ProductVariant product;

}
