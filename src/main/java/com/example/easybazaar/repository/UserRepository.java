package com.example.easybazaar.repository;

import com.example.easybazaar.dto.AllCustomersDto;
import com.example.easybazaar.dto.AllSellersDto;
import com.example.easybazaar.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select new com.example.easybazaar.dto.AllSellersDto" +
            "(user.id,user.name,user.companyName,user.rating,user.imagePath,user.imageName,user.address,user.email,user.isActive,user.contactNumber,user.city.name) " +
            "from User user where user.isActive = true and user.userType='SELLER' ")
     List<AllSellersDto> allSellers(Pageable pageable);

    @Query("select new com.example.easybazaar.dto.AllCustomersDto(user.id,user.name,user.registrationDate,user.imagePath,user.imageName,user.isActive,user.address,user.email,user.isVerified,user.contactNumber,user.cnic,user.dob,user.gender,user.city.name) from User user where user.isActive=true and user.userType='CUSTOMER' ")
     List<AllCustomersDto> allCustomers(Pageable pageable);

     User findByIdAndUserTypeAndIsActive(Long id, String userType,Boolean isActive);

     User findByEmail(String email);

     User findByCnic(String cnic);


     User findByEmailAndPasswordAndAndIsActive(String email, String password, Boolean isActive);

     @Query("SELECT count(user) from User user where user.userType='SELLER' and user.isActive = true ")
     Integer totalSellers();

    @Query("SELECT count(user) from User user where user.userType='CUSTOMER' and user.isActive = true ")
    Integer totalCustomers();

    @Query("SELECT count(user) from User user where user.userType='SHIPPER' and user.isActive = true ")
    Integer totalShippers();
}
