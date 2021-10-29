package com.example.easybazaar.repository;

import com.example.easybazaar.model.BankAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountDetailsRepository extends JpaRepository<BankAccountDetails,Long> {

}
