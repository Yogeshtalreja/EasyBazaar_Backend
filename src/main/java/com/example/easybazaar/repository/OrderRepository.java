package com.example.easybazaar.repository;

import com.example.easybazaar.dto.ShipperHistoryDto;
import com.example.easybazaar.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select new com.example.easybazaar.dto.ShipperHistoryDto(order.createdDate,order.orderId,order.shippingAddress) from Order order where order.shippedBy.id = ?1 and order.shippedBy.userType =?2")
    List<ShipperHistoryDto> findShipperHistoryById(Long shipperId , String userType);
}
