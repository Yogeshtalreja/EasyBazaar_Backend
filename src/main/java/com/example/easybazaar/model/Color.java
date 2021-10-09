package com.example.easybazaar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Color {
    @Id
    @Column(name = "id", nullable = false )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String colorName;

}
