package com.example.easybazaar.model;

import com.example.easybazaar.enums.TransactionTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tbl_order_details")
public class Transaction {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "totalAmount")
    private Long totalAmount;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "transaction_type")
    private TransactionTypeEnum transactionType;

}
