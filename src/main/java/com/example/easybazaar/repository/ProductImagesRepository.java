package com.example.easybazaar.repository;

import com.example.easybazaar.model.ProductImages;
import com.example.easybazaar.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {


    List<ProductImages> findByProduct(ProductVariant product);

}
