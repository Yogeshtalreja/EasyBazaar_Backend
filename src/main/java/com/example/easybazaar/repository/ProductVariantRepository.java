package com.example.easybazaar.repository;

import com.example.easybazaar.dto.AllSellerProductsDto;
import com.example.easybazaar.dto.ProductDto;
import com.example.easybazaar.model.ProductVariant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant,Long> {


    @Query("select new com.example.easybazaar.dto.AllSellerProductsDto(product.name,product.description,product.availableQuantity,product.sellPrice,product.rating,product.createdAt,product.expiryDate,product.weight,product.company) from ProductVariant product where product.sellerId = ?1 ")
     List<AllSellerProductsDto> allSellerProducts(Pageable pageable , Long sellerId);

     ProductVariant findByIdAndSellerId(Long id, Long sellerId);

     List<ProductVariant> findBySellerId(Long sellerId);

    @Query("SELECT product from ProductVariant product where product.id=?1")
     ProductVariant productById(Long id);

    @Query("SELECT new  com.example.easybazaar.dto.ProductDto(product.id,product.name,product.description,product.sellPrice,product.company) from ProductVariant product order by RAND()")
    List<ProductDto> allProducts(Pageable pageable);

}
