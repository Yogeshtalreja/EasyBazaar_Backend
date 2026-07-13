package com.example.easybazaar.controller;

import com.example.easybazaar.commonResponseModel.CommonResponseModel;
import com.example.easybazaar.dto.ShipperDto;
import com.example.easybazaar.service.ShipperService;
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
@RequestMapping("/shipper")
public class ShipperController {

    private final ShipperService shipperService;

    @PostMapping("/addShipper")
    public ResponseEntity<?> addShipper(@RequestBody ShipperDto shipperDto){
        CommonResponseModel<ShipperDto> responseModel = new CommonResponseModel<>();
        try {
            ShipperDto saveShipper = shipperService.addShipper(shipperDto);
            responseModel.setHasError(false);
            responseModel.setMessage("Shipper Saved Successfully");
            responseModel.setTotalCount(1);
            responseModel.setData(Collections.singletonList(saveShipper));
            return ResponseEntity.status(HttpStatus.OK).body(responseModel);
        } catch (Exception e) {
            responseModel.setHasError(true);
            responseModel.setMessage("Error  = " + e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }

    }

}
