package com.example.easybazaar.controller;

import com.example.easybazaar.Constants.Utils;
import com.example.easybazaar.commonResponseModel.CommonResponseModel;
import com.example.easybazaar.dto.OrderDto;
import com.example.easybazaar.dto.ProductDto;
import com.example.easybazaar.dto.search.SearchDto;
import com.example.easybazaar.model.Order;
import com.example.easybazaar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = Utils.crossOrigin)
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

    @PostMapping("/randomProducts")
    public ResponseEntity<?> randomProduct(@RequestBody SearchDto searchDto){
        CommonResponseModel<ProductDto> responseModel = new CommonResponseModel<>();
        try{
            List<ProductDto> productDtos = productService.allProducts(searchDto);
            responseModel.setHasError(false);
            responseModel.setMessage("Random Products");
            responseModel.setTotalCount(productDtos.size());
            responseModel.setData(productDtos);
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);

        }catch (Exception e){
            responseModel.setHasError(true);
            responseModel.setMessage("Error  = " + e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }
    }
}
