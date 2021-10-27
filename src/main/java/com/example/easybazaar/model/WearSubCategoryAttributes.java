package com.example.easybazaar.model;

import com.example.easybazaar.enums.WearType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import org.springframework.jca.endpoint.GenericMessageEndpointFactory;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tbl_wear_subcategory_attributes")
public class WearSubCategoryAttributes {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "material")
    private String material;

    @Column(name = "description")
    private String description;

    @Column(name = "wear_type")
    private String wearType;

    @Column(name = "products")
    @OneToMany(cascade = {CascadeType.ALL},targetEntity = ProductVariant.class)
    private List<ProductVariant> products;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    @JsonIgnore
    private Category category;
}
