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
@Table(name = "tbl_reviews")
public class Reviews {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "review")
    private String review;

    @Column(name = "stars")
    private Integer stars;

    @ManyToOne(targetEntity = Invoice.class)
    @JsonIgnore
    private Invoice invoice;

}
