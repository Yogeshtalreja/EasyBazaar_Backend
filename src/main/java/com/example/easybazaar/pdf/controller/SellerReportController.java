package com.example.easybazaar.pdf.controller;

import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.pdf.service.SellerReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/report")
public class SellerReportController {

    private final SellerReportService sellerReportService;

    @GetMapping("/sellerPdfReport/{sellerId}")
    public void generatePdf(@PathVariable("sellerId") Long sellerId , HttpServletResponse response) throws IOException, ResourceNotFoundException {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_"+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);

        this.sellerReportService.sellerProductsReport(response,sellerId);
    }

}
