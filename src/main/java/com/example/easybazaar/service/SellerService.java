package com.example.easybazaar.service;

import com.example.easybazaar.dto.AllSellersDto;
import com.example.easybazaar.dto.SellerDto;
import com.example.easybazaar.dto.search.SearchDto;
import com.example.easybazaar.enums.UserType;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.City;
import com.example.easybazaar.model.User;
import com.example.easybazaar.repository.CityRepository;
import com.example.easybazaar.repository.UserRepository;
import com.example.easybazaar.utilities.ValidationUtility;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class SellerService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public User addSeller(SellerDto sellerDto) throws ResourceNotFoundException {


        if(sellerDto.getEmail()==null)
            throw new ResourceNotFoundException("Email is Mandatory");

        if (!ValidationUtility.isValidNIC(sellerDto.getCnic()))
            throw new ResourceNotFoundException("CNIC is Not Valid");

//        if (ValidationUtility.isValidMobileNumber(sellerDto.getContactNumber()))
//            throw new ResourceNotFoundException("Contact Number is Not in Format");

        User user = new User();

        addSellerInformation(sellerDto, user);
        user.setUserType(UserType.SELLER.toString());
        user.setIsActive(true);

        return  userRepository.save(user);
    }

    public User updateSellerInfo(SellerDto sellerDto) throws ResourceNotFoundException {

        User user = userRepository.findById(sellerDto.getId()).orElseThrow(()-> new ResourceNotFoundException("User with ID "+sellerDto.getId()+" Not Found"));

        addSellerInformation(sellerDto, user);
        user.setIsActive(true);

        return  userRepository.save(user);
    }

    private void addSellerInformation(SellerDto sellerDto, User user) throws ResourceNotFoundException {

        if (sellerDto.getName()!=null)
            user.setName(sellerDto.getName());
        if (sellerDto.getAddress()!=null)
            user.setAddress(sellerDto.getAddress());
        if (sellerDto.getCnic()!=null){
            if(!ValidationUtility.isValidNIC(sellerDto.getCnic()))
                throw new ResourceNotFoundException("CNIC Format is Incorrect");
            user.setCnic(sellerDto.getCnic());
        }

        if (sellerDto.getCompanyName()!=null)
            user.setCompanyName(sellerDto.getCompanyName());
        if (sellerDto.getEmail()!=null)
            user.setEmail(sellerDto.getEmail());
        if (sellerDto.getContactNumber()!=null)
            user.setContactNumber(sellerDto.getContactNumber());
        if (sellerDto.getPassword()!=null)
            user.setPassword(sellerDto.getPassword());
        if (sellerDto.getRegistrationDate()!=null)
            user.setRegistrationDate(sellerDto.getRegistrationDate());
        if (sellerDto.getCityId()!=null){

            City city = cityRepository.findById(sellerDto.getCityId()).orElseThrow(()-> new ResourceNotFoundException
                    ("City with ID "+sellerDto.getCityId()+" Not Found"));
            user.setCity(city);

        }
    }

    public User deleteSeller(Long sellerId) throws ResourceNotFoundException {

        User user = userRepository.findById(sellerId).orElseThrow
                (()-> new ResourceNotFoundException("User with ID "+sellerId+" Not Found"));

        user.setIsActive(false);

        return userRepository.save(user);
    }

    public List<AllSellersDto> allSellersInfo(SearchDto searchDto){

        Pageable pageable = PageRequest.of(searchDto.getPageNo(), searchDto.getPageSize());
        List<AllSellersDto> allSellers =  userRepository.allSellers(pageable);

        return allSellers;
    }
}
