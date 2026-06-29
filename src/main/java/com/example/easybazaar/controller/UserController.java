package com.example.easybazaar.controller;

import com.example.easybazaar.commonResponseModel.CommonResponseModel;
import com.example.easybazaar.dto.SignInRequest;
import com.example.easybazaar.dto.SignInRes;
import com.example.easybazaar.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @PostMapping("/signIn")
    public ResponseEntity<?> isAuthenticated(@RequestBody SignInRequest signInRequest){

        CommonResponseModel<SignInRes> commonResponseModel = new CommonResponseModel<>();
        try{
            SignInRes isValid = userService.isValidAuthentication(signInRequest);
            commonResponseModel.setTotalCount(1);
            commonResponseModel.setMessage("Valid Credentials");
            commonResponseModel.setHasError(false);
            commonResponseModel.setData(Collections.singletonList(isValid));
        return ResponseEntity.status(HttpStatus.OK).body(commonResponseModel);
        }catch (Exception e){
            commonResponseModel.setTotalCount(0);
            commonResponseModel.setMessage(e.getMessage());
            commonResponseModel.setHasError(true);
            commonResponseModel.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commonResponseModel);

        }

    }

}
