package com.example.easybazaar.repository;

import com.example.easybazaar.model.WearSubCategoryAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebSubCategoriesAttributesRepository extends JpaRepository<WearSubCategoryAttributes,Long> {
}
