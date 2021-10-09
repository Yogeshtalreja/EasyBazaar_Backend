package com.example.easybazaar.repository;

import com.example.easybazaar.dto.AllSellerProductsDto;
import com.example.easybazaar.model.ProductVariant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant,Long> {


    @Query("select new com.example.easybazaar.dto.AllSellerProductsDto(product.name,product.description,product.availableQuantity,product.sellPrice,product.rating,product.createdAt,product.expiryDate,product.weight,product.company) from ProductVariant product where product.sellerId.id = ?1 ")
    public List<AllSellerProductsDto> allSellerProducts(Pageable pageable , Long sellerId);

}
