package com.example.easybazaar.controller;

import com.example.easybazaar.Constants.Utils;
import com.example.easybazaar.commonResponseModel.CommonResponseModel;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.ProductImages;
import com.example.easybazaar.service.ProductPictureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = Utils.crossOrigin)
@RequestMapping("/productImage")
@Slf4j
public class ProductPicturesController {

    private final ProductPictureService productPictureService;

    @GetMapping("/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        try {
            Resource file = productPictureService.load(filename);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (Exception e) {
            log.error(e.getMessage() + "\n" + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String("Error: " + e));
        }
    }

    @PostMapping("/upload/{productId}")
    public ResponseEntity<?> uploadPropertyDocOrImages(@PathVariable("productId") Long productId, @RequestParam("file") MultipartFile[] file) throws Exception {
        CommonResponseModel<String> responseModel = new CommonResponseModel<>();
        try {

            List<String> fileNames = new ArrayList<>();
            Arrays.asList(file).forEach(fille -> {
                try {
                    fileNames.add(productPictureService.saveProductPictures(productId,fille));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
            responseModel.setHasError(false);
            responseModel.setMessage("Product Pictures Response");
            responseModel.setTotalCount(fileNames.size());
            responseModel.setData(fileNames);
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            responseModel.setHasError(true);
            responseModel.setMessage("Error  = " + e.getMessage());
            responseModel.setTotalCount(0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);


        }

    }


    @GetMapping("/delete/{productId}")
    public List<ProductImages> deleteImages(@PathVariable("productId") long productId) throws ResourceNotFoundException {

        return productPictureService.deletePictures(productId);
    }

}
