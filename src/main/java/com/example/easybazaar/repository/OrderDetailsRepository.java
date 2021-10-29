package com.example.easybazaar.repository;

import com.example.easybazaar.model.OrderDetails;
import com.example.easybazaar.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {

    List<OrderDetails> findByProduct(ProductVariant productVariant);
}
