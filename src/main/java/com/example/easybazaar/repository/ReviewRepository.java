package com.example.easybazaar.repository;

import com.example.easybazaar.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Reviews,Long> {
}
