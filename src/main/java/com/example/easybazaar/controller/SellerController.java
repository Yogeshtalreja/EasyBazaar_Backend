package com.example.easybazaar.controller;

import com.example.easybazaar.commonResponseModel.CommonResponseModel;
import com.example.easybazaar.dto.SellerDto;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.User;
import com.example.easybazaar.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/seller")
@AllArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping("/addSeller")
    public ResponseEntity<?> addSeller(@RequestBody SellerDto sellerDto){

        CommonResponseModel<User> responseModel = new CommonResponseModel<>();

        try {
            User user = sellerService.addSeller(sellerDto);
            responseModel.setHasError(false);
            responseModel.setMessage("Seller Response");
            responseModel.setTotalCount(1);
            responseModel.setData(Collections.singletonList(user));
          return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        } catch (Exception e) {
            responseModel.setHasError(true);
            responseModel.setMessage("Error  = " + e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }

    }
}
