package com.example.easybazaar.controller;

import com.example.easybazaar.commonResponseModel.CommonResponseModel;
import com.example.easybazaar.dto.OrderDto;
import com.example.easybazaar.dto.TotalSellersCustomerDto;
import com.example.easybazaar.model.Order;
import com.example.easybazaar.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@AllArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

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

}
