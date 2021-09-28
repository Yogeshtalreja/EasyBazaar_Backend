package com.example.easybazaar.service;

import com.example.easybazaar.dto.SellerDto;
import com.example.easybazaar.enums.UserType;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.City;
import com.example.easybazaar.model.User;
import com.example.easybazaar.repository.CityRepository;
import com.example.easybazaar.repository.UserRepository;
import com.example.easybazaar.utilities.ValidationUtility;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.Collections;

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

        if (sellerDto.getName()!=null)
            user.setName(sellerDto.getName());
        if (sellerDto.getAddress()!=null)
            user.setAddress(sellerDto.getAddress());
        if (sellerDto.getCnic()!=null)
            user.setCnic(sellerDto.getCnic());
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
        user.setUserType(Collections.singleton(UserType.SELLER));
        user.setIsActive(true);

        return  userRepository.save(user);
    }


}
