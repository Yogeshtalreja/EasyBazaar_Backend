package com.example.easybazaar.model;

import com.example.easybazaar.enums.WearType;
import lombok.*;
import org.springframework.jca.endpoint.GenericMessageEndpointFactory;

import javax.persistence.*;

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

    @Column(name = "wearType")
    private WearType wearType;

}
