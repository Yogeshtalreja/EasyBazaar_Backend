package com.example.easybazaar.service;

import com.example.easybazaar.controller.ProductPicturesController;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.ProductImages;
import com.example.easybazaar.model.ProductVariant;
import com.example.easybazaar.repository.ProductImagesRepository;
import com.example.easybazaar.repository.ProductVariantRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductPictureService {

    public static final Path uploadDirectory = Paths.get("uploads");
    private final ProductImagesRepository productImagesRepository;
    private final ProductVariantRepository productVariantRepository;

    public Resource load(String filename) throws Exception {
        try {
            Path file = uploadDirectory.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional(rollbackOn = {Exception.class})
    public String saveProductPictures(Long propertyId, MultipartFile file) throws ResourceNotFoundException, IOException {

            ProductVariant product = productVariantRepository.findById(propertyId)
                    .orElseThrow(()-> new ResourceNotFoundException("product with ID "+propertyId+" Not Found"));
            ProductImages productImages = new ProductImages();
            productImages.setProduct(product);
            productImages.setName(file.getOriginalFilename());
            ProductImages saved = productImagesRepository.save(productImages);
            String imageName = UUID.randomUUID().toString();
            Files.copy(file.getInputStream(), uploadDirectory.resolve(saved.getId() + "-" + imageName+"."+file.getContentType().substring(6)));
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ProductPicturesController.class, "getFile", saved.getId() + "-" + imageName+"."+file.getContentType().substring(6)).build().toString();
            saved.setUrl(url);
            saved.setPath(uploadDirectory + "/" + saved.getId() + "-" + imageName+"."+file.getContentType().substring(6));
            productImagesRepository.save(saved);
            return url;

          //  throw new Exception("Could not store the file. Error: " + e.getMessage() + "\n" + e);

    }

}
