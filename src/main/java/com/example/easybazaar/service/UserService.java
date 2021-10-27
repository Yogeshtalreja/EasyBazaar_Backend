package com.example.easybazaar.service;

import com.example.easybazaar.dto.SignInRequest;
import com.example.easybazaar.encryption.AES;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.User;
import com.example.easybazaar.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    @Value("${aes.secrete}")
//    private String aesKey;

    public Boolean isValidAuthentication(SignInRequest signInRequest) throws ResourceNotFoundException {

        if (signInRequest.getEmail()==null || signInRequest.getPassword()==null){
            throw new ResourceNotFoundException("Email or Password can not be Null");
        }else {

            String encryptedPassword = AES.encrypt(signInRequest.getPassword(),"EASYBAZ");
            User user = userRepository.findByEmailAndPasswordAndAndIsActive(signInRequest.getEmail(), encryptedPassword,true);
            if (user == null)
                throw new ResourceNotFoundException("Email or Password is Incorrect");
            else
                return true;
        }

    }
}
