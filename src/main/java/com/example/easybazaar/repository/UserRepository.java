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
    public List<AllSellersDto> allSellers(Pageable pageable);

    @Query("select new com.example.easybazaar.dto.AllCustomersDto(user.id,user.name,user.registrationDate,user.imagePath,user.imageName,user.isActive,user.address,user.email,user.isVerified,user.contactNumber,user.cnic,user.dob,user.gender,user.city.name) from User user where user.isActive=true and user.userType='CUSTOMER' ")
    public List<AllCustomersDto> allCustomers(Pageable pageable);

    public User findByIdAndUserTypeAndIsActive(Long id, String userType,Boolean isActive);

    public User findByEmail(String email);

}
