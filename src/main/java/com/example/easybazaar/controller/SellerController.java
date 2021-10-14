package com.example.easybazaar.controller;

import com.example.easybazaar.commonResponseModel.CommonResponseModel;
import com.example.easybazaar.dto.*;
import com.example.easybazaar.dto.search.SearchDto;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.ProductVariant;
import com.example.easybazaar.model.User;
import com.example.easybazaar.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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

    @PostMapping("/updateSeller")
    public ResponseEntity<?> updateSeller(@RequestBody SellerDto sellerDto){
        CommonResponseModel<User> responseModel = new CommonResponseModel<>();
        try {
            User user = sellerService.updateSellerInfo(sellerDto);
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

    @DeleteMapping("/deleteSeller/{sellerId}")
    public ResponseEntity<?> deleteSeller(@PathVariable Long sellerId){
        CommonResponseModel<User> responseModel = new CommonResponseModel<>();
        try {
            User user = sellerService.deleteSeller(sellerId);
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

    @PostMapping("/allSellers")
    public ResponseEntity<?> allSellers(@RequestBody SearchDto searchDto){
        CommonResponseModel<AllSellersDto> responseModel = new CommonResponseModel<>();
        try {
            List<AllSellersDto> allSellers = (List<AllSellersDto>) sellerService.allSellersInfo(searchDto);
            responseModel.setHasError(false);
            responseModel.setMessage("Seller Response");
            responseModel.setTotalCount(allSellers.size());
            responseModel.setData(allSellers);
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        } catch (Exception e) {
            responseModel.setHasError(true);
            responseModel.setMessage("Error  = " + e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }

    }
    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody AddProductDto addProductDto){
        CommonResponseModel<ProductVariant> responseModel = new CommonResponseModel<>();
        try {
            ProductVariant addedProduct = sellerService.addProduct(addProductDto);
            responseModel.setHasError(false);
            responseModel.setMessage("Seller Product Response");
            responseModel.setTotalCount(1);
            responseModel.setData(Collections.singletonList(addedProduct));
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        } catch (Exception e) {
            responseModel.setHasError(true);
            responseModel.setMessage("Error  = " + e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }

    }
    @PostMapping("/allProducts/{sellerId}")
    public ResponseEntity<?> allProducts(@PathVariable("sellerId") Long sellerId , @RequestBody SearchDto searchDto){
        CommonResponseModel<AllSellerProductsDto> responseModel = new CommonResponseModel<>();
        try {
            List<AllSellerProductsDto> allSellerProducts = sellerService.allSellerProductsDto(searchDto, sellerId);
            responseModel.setHasError(false);
            responseModel.setMessage("Seller Product Response");
            responseModel.setTotalCount(1);
            responseModel.setData(allSellerProducts);
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        } catch (Exception e) {
            responseModel.setHasError(true);
            responseModel.setMessage("Error  = " + e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }

    }

    @PostMapping("/signUp}")
    public ResponseEntity<?> signUpSeller(@RequestBody SignUpDto signUpDto){
        CommonResponseModel<SignUpDto> responseModel = new CommonResponseModel<>();
        try {
            SignUpDto resultSignUpDto = sellerService.singUpSeller(signUpDto);
            responseModel.setHasError(false);
            responseModel.setMessage("Seller SignUp Successful");
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

}
