package com.example.easybazaar.service;

import com.example.easybazaar.dto.ShipperDto;
import com.example.easybazaar.encryption.AES;
import com.example.easybazaar.enums.UserType;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.City;
import com.example.easybazaar.model.User;
import com.example.easybazaar.repository.CityRepository;
import com.example.easybazaar.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ShipperService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public ShipperDto addShipper(ShipperDto shipperDto) throws ResourceNotFoundException {

        if (shipperDto.getEmail()==null)
            throw new ResourceNotFoundException("Email is Mandatory");
        if (shipperDto.getPassword()==null)
            throw new ResourceNotFoundException("Password is Mandatory");
        if (shipperDto.getCnic()==null)
            throw new ResourceNotFoundException("NIC is Mandatory");
        if (shipperDto.getContactNumber()==null)
            throw new ResourceNotFoundException("Contact Number is Mandatory");

        User user = userRepository.findByEmail(shipperDto.getEmail());
        if (user!=null)
            throw new ResourceNotFoundException("User With this Email is Already Existing");

        User newShipper = new User();

        newShipper.setEmail(shipperDto.getEmail());
        newShipper.setPassword(AES.encrypt(shipperDto.getPassword(),"EASYBAZ"));
        newShipper.setCnic(shipperDto.getCnic());
        newShipper.setContactNumber(shipperDto.getContactNumber());
        newShipper.setIsActive(true);
        newShipper.setUserType(UserType.SHIPPER.toString());
        newShipper.setRegistrationDate(LocalDate.now());
        if(shipperDto.getAddress()!=null)
            user.setAddress(newShipper.getAddress());
        if (shipperDto.getName()!=null)
            user.setName(newShipper.getName());
        if (shipperDto.getDob()!=null)
            user.setDob(newShipper.getDob());
        if (shipperDto.getCityId()!=null){

            City city = cityRepository.findById(shipperDto.getCityId()).orElseThrow(
                    ()-> new ResourceNotFoundException("City with ID "+shipperDto.getCityId()+" " +
                            "Not Found")
            );
            newShipper.setCity(city);
        }

        shipperDto.setPassword(AES.encrypt(shipperDto.getPassword(),"EASYBAZ"));

        userRepository.save(newShipper);

        return shipperDto;
    }

}
