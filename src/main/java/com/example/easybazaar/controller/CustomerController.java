package com.example.easybazaar.controller;

import com.example.easybazaar.Constants.Utils;
import com.example.easybazaar.commonResponseModel.CommonResponseModel;
import com.example.easybazaar.dto.AllCustomersDto;
import com.example.easybazaar.dto.CustomerDto;
import com.example.easybazaar.dto.SignUpDto;
import com.example.easybazaar.dto.search.SearchDto;
import com.example.easybazaar.model.User;
import com.example.easybazaar.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
@CrossOrigin(origins = Utils.crossOrigin)
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDto customerDto){
        CommonResponseModel<User> responseModel = new CommonResponseModel<>();
        try {
            User user = customerService.addCustomer(customerDto);
            responseModel.setHasError(false);
            responseModel.setMessage("Customer Response");
            responseModel.setTotalCount(1);
            responseModel.setData(Collections.singletonList(user));
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        }catch (Exception e){
            responseModel.setHasError(true);
            responseModel.setMessage(e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }

    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUpSeller(@RequestBody SignUpDto signUpDto){
        CommonResponseModel<SignUpDto> responseModel = new CommonResponseModel<>();
        try {
            SignUpDto resultSignUpDto = customerService.signUpCustomer(signUpDto);
            responseModel.setHasError(false);
            responseModel.setMessage("Customer SignUp Successful");
            responseModel.setTotalCount(1);
            responseModel.setData(Collections.singletonList(resultSignUpDto));
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        } catch (Exception e) {
            responseModel.setHasError(true);
            responseModel.setMessage("Error  = " + e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }

    }

    @PostMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto){
        CommonResponseModel<User> responseModel = new CommonResponseModel<>();
        try {
            User user = customerService.updateCustomerInfo(customerDto);
            responseModel.setHasError(false);
            responseModel.setMessage("Customer Response");
            responseModel.setTotalCount(1);
            responseModel.setData(Collections.singletonList(user));
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        }catch (Exception e){
            responseModel.setHasError(true);
            responseModel.setMessage(e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId){
        CommonResponseModel<User> responseModel = new CommonResponseModel<>();
        try {
            User user = customerService.deleteCustomer(customerId);
            responseModel.setHasError(false);
            responseModel.setMessage("Customer Response");
            responseModel.setTotalCount(1);
            responseModel.setData(Collections.singletonList(user));
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        }catch (Exception e){
            responseModel.setHasError(true);
            responseModel.setMessage(e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }
    }

    @PostMapping("/allCustomers")
    public ResponseEntity<?> allCustomers(@RequestBody SearchDto searchDto){
        CommonResponseModel<AllCustomersDto> responseModel = new CommonResponseModel<>();
        try {
            List<AllCustomersDto> allCustomers  = customerService.allCustomers(searchDto);
            responseModel.setHasError(false);
            responseModel.setMessage("Customer Response");
            responseModel.setTotalCount(allCustomers.size());
            responseModel.setData(allCustomers);
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        }catch (Exception e){
            responseModel.setHasError(true);
            responseModel.setMessage(e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }
    }

}
