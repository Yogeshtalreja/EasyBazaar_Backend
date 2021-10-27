package com.example.easybazaar.pdf.service;

import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.OrderDetails;
import com.example.easybazaar.model.ProductVariant;
import com.example.easybazaar.model.User;
import com.example.easybazaar.repository.OrderDetailsRepository;
import com.example.easybazaar.repository.ProductVariantRepository;
import com.example.easybazaar.repository.UserRepository;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;


import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class SellerReportService {

    private final UserRepository userRepository;
    private final ProductVariantRepository productVariantRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public void sellerProductsReport(HttpServletResponse response , Long sellerId) throws ResourceNotFoundException, IOException {

        User user = userRepository.findByIdAndUserTypeAndIsActive(sellerId,"SELLER",true);
        if (user == null)
            throw new ResourceNotFoundException("Seller Not Found with ID "+sellerId+"");

        Document document =  new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(Color.green);
        fontTitle.isBold();
        Paragraph easyBazaarTitle =new Paragraph("EASYBAZAAR",fontTitle);
        easyBazaarTitle.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(easyBazaarTitle);

        Font headerFont = FontFactory.getFont(FontFactory.COURIER);
        headerFont.setSize(12);

        Paragraph headerParagraphOne = new Paragraph("Seller Name : "+user.getName(),headerFont);
        Paragraph headerParagraphTwo = new Paragraph("Seller NIC : "+user.getCnic(),headerFont);
        Paragraph headerParagraphThree = new Paragraph("Seller Email : "+user.getEmail(),headerFont);
        headerParagraphOne.setAlignment(Element.ALIGN_LEFT);
        document.add(headerParagraphOne);
        headerParagraphTwo.setAlignment(Element.ALIGN_LEFT);
        document.add(headerParagraphTwo);
        headerParagraphThree.setAlignment(Element.ALIGN_LEFT);
        document.add(headerParagraphThree);

        Paragraph lineBreakPara = new Paragraph("");
        document.add(lineBreakPara);
        List<ProductVariant> sellerProducts = productVariantRepository.findBySellerId(sellerId);

   //     float[] columnWidth = {200f,200f,200f,200f};

        PdfPTable table = new PdfPTable(5);

        PdfPCell pdfHeaderOne = new PdfPCell(new Paragraph("Product Name"));
        PdfPCell pdfHeaderTwo = new PdfPCell(new Paragraph("Product Rating"));
        PdfPCell pdfHeaderThree = new PdfPCell(new Paragraph("Available Quantities"));
        PdfPCell pdfHeaderFour = new PdfPCell(new Paragraph("Sell Products No "));
        PdfPCell pdfHeaderFive = new PdfPCell(new Paragraph("Sell Amount"));

        table.addCell(pdfHeaderOne);
        table.addCell(pdfHeaderTwo);
        table.addCell(pdfHeaderThree);
        table.addCell(pdfHeaderFour);
        table.addCell(pdfHeaderFive);


        for (ProductVariant product:sellerProducts) {
            PdfPCell pdfCol1 = new PdfPCell(new Paragraph(product.getName()));
            PdfPCell pdfCol2 = new PdfPCell(new Paragraph(""+product.getRating()));
            PdfPCell pdfCol3 = new PdfPCell(new Paragraph(""+product.getAvailableQuantity()));

            List<OrderDetails> orderDetails = orderDetailsRepository.findByProduct(product);
            Integer totalSelledProducts = 0;
            for (OrderDetails orderDetailss:orderDetails) {
                totalSelledProducts+= orderDetailss.getQuantity();
            }

           // Double sellAmount = (product.getSellPrice() - ((product.getSellPrice() * 0.15) - ((product.getSellPrice() * 0.15)* 0.15)));

            PdfPCell pdfCol4 = new PdfPCell(new Paragraph(""+ ((product.getSellPrice()/1.15)*totalSelledProducts) +" PKR ") );


            //            PdfPCell pdfPCell5 =
            PdfPCell pdfCol5 = new PdfPCell(new Paragraph(""+totalSelledProducts));
            table.addCell(pdfCol1);
            table.addCell(pdfCol2);
            table.addCell(pdfCol3);
            table.addCell(pdfCol5);
            table.addCell(pdfCol4);

        }

        document.add(table);
        document.close();

    }
}
