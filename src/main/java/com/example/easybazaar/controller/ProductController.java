package com.example.easybazaar.controller;

import com.example.easybazaar.commonResponseModel.CommonResponseModel;
import com.example.easybazaar.dto.OrderDto;
import com.example.easybazaar.model.Order;
import com.example.easybazaar.service.ProductService;
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
@RequestMapping("/customer")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/purchaseProduct")
    public ResponseEntity<?> purchaseProduct(@RequestBody OrderDto orderDto){
        CommonResponseModel<Order> responseModel = new CommonResponseModel<>();
        try{
            Order order = productService.productPurchase(orderDto);
            responseModel.setHasError(false);
            responseModel.setMessage("Product Purchased Successfully");
            responseModel.setTotalCount(1);
            responseModel.setData(Collections.singletonList(order));
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);

        }catch (Exception e){
            responseModel.setHasError(true);
            responseModel.setMessage("Error  = " + e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }
    }
}
