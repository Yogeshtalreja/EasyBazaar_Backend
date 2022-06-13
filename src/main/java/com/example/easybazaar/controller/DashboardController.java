package com.example.easybazaar.controller;

import com.example.easybazaar.Constants.Utils;
import com.example.easybazaar.commonResponseModel.CommonResponseModel;
import com.example.easybazaar.dto.AllCitiesDto;
import com.example.easybazaar.dto.ProductDto;
import com.example.easybazaar.dto.TotalSellersCustomerDto;
import com.example.easybazaar.dto.search.SearchDto;
import com.example.easybazaar.service.CityService;
import com.example.easybazaar.service.DashboardService;
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
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    private final ProductService productService;
    private final CityService cityService;
    @GetMapping("/total")
    public ResponseEntity<?> totalSellersCustomers(){
        CommonResponseModel<TotalSellersCustomerDto> responseModel = new CommonResponseModel<>();
        try{
            TotalSellersCustomerDto totalSellersCustomerDto = dashboardService.totalSellersCustomerDto();
            responseModel.setHasError(false);
            responseModel.setMessage("The totals");
            responseModel.setTotalCount(1);
            responseModel.setData(Collections.singletonList(totalSellersCustomerDto));
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

    @PostMapping("/sortProductsByName")
    public ResponseEntity<?> SortProductsByName(@RequestBody SearchDto searchDto){
        CommonResponseModel<ProductDto> responseModel = new CommonResponseModel<>();
        try{
            List<ProductDto> productDtos = productService.allProductsSortByName(searchDto);
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

    @GetMapping("/allCities")
    public ResponseEntity<?> allCities(){
        CommonResponseModel<AllCitiesDto> responseModel = new CommonResponseModel<>();
        try{
           List<AllCitiesDto> cities = cityService.allCities();
            responseModel.setHasError(false);
            responseModel.setMessage("All Cities");
            responseModel.setTotalCount(cities.size());
            responseModel.setData(cities);
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);

        }catch (Exception e){
            responseModel.setHasError(true);
            responseModel.setMessage("Error  = " + e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }
    }

}
