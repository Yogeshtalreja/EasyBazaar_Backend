package com.example.easybazaar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tbl_electronic_subcategories")
public class ElectronicSubCategoriesAttributes {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "processor")
    private Long processor;

    @Column(name = "number_of_cpus")
    private Long numberOfCPUs;

    @Column(name = "number_of_ports")
    private Long numberOfPorts;

    @Column(name = "cpu_speed")
    private Long cpuSpeed;

    @Column(name = "model")
    private String model;

    @Column(name = "description")
    private String description;

    @Column(name = "OS")
    private String OS;

    @Column(name = "ram")
    private Long ram;

    @Column(name = "ssd")
    private Long ssd;

    @Column(name = "hdd")
    private Long hdd;

    @Column(name = "screen_resoltuion")
    private String screenResolution;

    @Column(name = "graphic_card")
    private Long graphicCard;

    @Column(name = "chip_brand")
    private String chipBrand;

    @Column(name = "battery_life")
    private Long batteryLife;

    @Column(name = "products")
    @OneToMany(cascade = {CascadeType.ALL},targetEntity = ProductVariant.class)
    private List<ProductVariant> products;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    @JsonIgnore
    private Category category;

}
