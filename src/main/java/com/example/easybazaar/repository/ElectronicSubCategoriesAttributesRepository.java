package com.example.easybazaar.repository;

import com.example.easybazaar.model.ElectronicSubCategoriesAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectronicSubCategoriesAttributesRepository extends JpaRepository<ElectronicSubCategoriesAttributes,Long> {
}
