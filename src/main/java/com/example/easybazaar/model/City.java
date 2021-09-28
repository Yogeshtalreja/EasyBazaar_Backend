package com.example.easybazaar.model;

import com.example.easybazaar.enums.StateEnum;
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
@Table(name = "tbl_city")
public class    City {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "state")
    private StateEnum state;

    @OneToMany(cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<User> user;
}
