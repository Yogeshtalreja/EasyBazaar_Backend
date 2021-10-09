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
@Table(name = "tbl_category")
public class Category {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "subCategory")
    private String subCategory;

    @Column(name = "electronic_subcategories")
    @OneToMany(cascade = {CascadeType.ALL},targetEntity = ElectronicSubCategoriesAttributes.class)
    private List<ElectronicSubCategoriesAttributes> electronicSubCategories;

    @Column(name = "electronic_subcategories")
    @OneToMany(cascade = {CascadeType.ALL},targetEntity = WearSubCategoryAttributes.class)
    private List<WearSubCategoryAttributes> wearSubCategories;


}
