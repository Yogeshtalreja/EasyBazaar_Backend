package com.example.easybazaar.model;

import com.example.easybazaar.enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tbl_order")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "status")
    private OrderStatusEnum status;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "delivere_Date")
    private LocalDate deliveredDate;

    @Column(name = "order_id")
    private String orderId;

    @OneToMany(mappedBy = "order",cascade = {CascadeType.ALL})
    private List<OrderDetails> orderDetails;

    @OneToOne
    private Invoice invoice;
}
