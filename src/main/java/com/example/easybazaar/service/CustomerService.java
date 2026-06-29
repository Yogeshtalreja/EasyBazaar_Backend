package com.example.easybazaar.service;

import com.example.easybazaar.dto.AllCustomersDto;
import com.example.easybazaar.dto.CustomerDto;
import com.example.easybazaar.dto.SignUpDto;
import com.example.easybazaar.dto.search.SearchDto;
import com.example.easybazaar.encryption.AES;
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


import java.time.LocalDate;
import java.util.List;

import static com.example.easybazaar.enums.UserType.CUSTOMER;

@Service
@AllArgsConstructor
public class CustomerService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public User addCustomer(CustomerDto customerDto) throws ResourceNotFoundException {

        if (customerDto.getEmail()==null)
            throw new ResourceNotFoundException("Email is Mandatory");

        if (customerDto.getCnic()==null)
            throw new ResourceNotFoundException("NIC is Mandatory");

        if (customerDto.getPassword()==null)
            throw new ResourceNotFoundException("Password is Mandatory");

        if (!ValidationUtility.isValidNIC(customerDto.getCnic()))
            throw new ResourceNotFoundException("NIC is Not Valid");

        User user = new User();
        addCustomerInformation(customerDto, user);
        user.setUserType(CUSTOMER.toString());
        user.setIsActive(true);

        return  userRepository.save(user);

    }

    public User updateCustomerInfo(CustomerDto customerDto) throws ResourceNotFoundException {

        if (customerDto.getId()==null)
            throw new ResourceNotFoundException("ID is Mandatory");

        User user = userRepository.findById(customerDto.getId()).orElseThrow(()
                -> new ResourceNotFoundException("Customer With ID not Found"));

        addCustomerInformation(customerDto, user);
        user.setUserType(CUSTOMER.toString());
        user.setIsActive(true);

        return  userRepository.save(user);

    }


    public User deleteCustomer(Long customerId) throws ResourceNotFoundException {

        User user = userRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer With ID "+customerId+" Not Found"));

        user.setIsActive(false);

        return userRepository.save(user);
    }

    private void addCustomerInformation(CustomerDto customerDto, User user) throws ResourceNotFoundException {


        if (customerDto.getName()!=null)
            user.setName(customerDto.getName());
        if (customerDto.getAddress()!=null)
            user.setAddress(customerDto.getAddress());
        if (customerDto.getCnic()!=null){
            if(!ValidationUtility.isValidNIC(customerDto.getCnic()))
                throw new ResourceNotFoundException("CNIC Format is Incorrect");
            user.setCnic(customerDto.getCnic());
        }

        if (customerDto.getEmail()!=null)
            user.setEmail(customerDto.getEmail());
        if (customerDto.getContactNumber()!=null)
            user.setContactNumber(customerDto.getContactNumber());
        if (customerDto.getPassword()!=null)
            user.setPassword(AES.encrypt(customerDto.getPassword(),"EASYBAZ"));

        user.setRegistrationDate(LocalDate.now());

        if (customerDto.getDob()!=null)
            user.setDob(customerDto.getDob());

        if (customerDto.getGender()!=null){
//            if (customerDto.getGender().equals("MALE"))
//                user.setGender(MALE.toString());
//            if (customerDto.getGender().equals("FEMALE"))
//                user.setGender(FEMALE.toString());
//            else
//                throw new ResourceNotFoundException("Gender is Incorrect");
            user.setGender(customerDto.getGender());
        }

        if (customerDto.getCityId()!=null){
            City city = cityRepository.findById(customerDto.getCityId())
                    .orElseThrow(()-> new ResourceNotFoundException
                    ("City with ID "+customerDto.getCityId()+" Not Found"));
            user.setCity(city);
        }

    }

    public SignUpDto signUpCustomer(SignUpDto signUp) throws ResourceNotFoundException {

        User user = userRepository.findByEmail(signUp.getEmail());

        if (signUp.getFirstName()==null || signUp.getPassword()==null || signUp.getEmail()==null || signUp.getLastName()==null )
            throw new ResourceNotFoundException("Enter All Details");

        if (user!=null)
            throw new ResourceNotFoundException("User With This Email is Already Exists");

        User newUser = new User();
        newUser.setName(signUp.getFirstName()+" "+signUp.getLastName());
        newUser.setEmail(signUp.getEmail());
        String encryptedPassword = AES.encrypt(signUp.getPassword(),"EASYBAZ");
        newUser.setPassword(encryptedPassword);
        newUser.setUserType(CUSTOMER.toString());
        userRepository.save(newUser);
        signUp.setPassword(encryptedPassword);

        return signUp;
    }

    public List<AllCustomersDto> allCustomers(SearchDto searchDto){

        Pageable pageable = PageRequest.of(searchDto.getPageNo(),searchDto.getPageSize());

        return userRepository.allCustomers(pageable);
    }

}
