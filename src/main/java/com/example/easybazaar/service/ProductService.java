package com.example.easybazaar.service;

import com.example.easybazaar.dto.OrderDetailsDto;
import com.example.easybazaar.dto.OrderDto;
import com.example.easybazaar.dto.ProductDto;
import com.example.easybazaar.dto.search.SearchDto;
import com.example.easybazaar.enums.OrderStatusEnum;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.*;
import com.example.easybazaar.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {
    private final UserRepository userRepository;
    private final ProductVariantRepository productVariantRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;
    private final ProductImagesRepository productImagesRepository;

    @Transactional(rollbackOn = {ResourceNotFoundException.class , Exception.class})
    public Order productPurchase(OrderDto orderDto) throws ResourceNotFoundException {
        if (orderDto.getCustomerId()==null)
            throw new ResourceNotFoundException("Customer ID is Missing");

        if (orderDto.getShippingAddress()==null)
            throw new ResourceNotFoundException("Please Enter Shipping Address First");

        Long totalPrice = 0L;

        User customer = userRepository.findByIdAndUserTypeAndIsActive(orderDto.getCustomerId(), "CUSTOMER",true);
        if (customer == null)
            throw new ResourceNotFoundException("CUSTOMER With ID "+orderDto.getCustomerId()+" Not Found");

        Order newOrder = new Order();
        newOrder.setOrderId(UUID.randomUUID().toString());
        newOrder.setCreatedDate(LocalDate.now());
        newOrder.setStatus(OrderStatusEnum.IN_PROGRESS);
        newOrder.setDeliveredDate(LocalDate.now().plusDays(2));
        newOrder.setShippingAddress(orderDto.getShippingAddress());

        Order saveOrder = orderRepository.save(newOrder);


        if (orderDto.getOrderDetails()!=null){
            List<ProductVariant> products = new ArrayList<>();
            for (OrderDetailsDto orderDetails: orderDto.getOrderDetails()) {

                if (orderDetails.getProductId()==null)
                    throw new ResourceNotFoundException("Product ID is Missing");

                    ProductVariant product = productVariantRepository.productById(orderDetails.getProductId());
                    if (product==null)
                        throw new ResourceNotFoundException("Not Found");
                    log.info("Locah Hai");

                if (product.getAvailableQuantity() < orderDetails.getQuantity())
                    throw new ResourceNotFoundException(product.getName()+" has only "+product.getAvailableQuantity()+" Quantity");

                OrderDetails newOrderDetail = new OrderDetails();
                newOrderDetail.setProduct(product);
                if (orderDetails.getQuantity()==null)
                    throw new ResourceNotFoundException("Enter the Quantites");
                if (orderDetails.getDescription()!=null)
                    newOrderDetail.setDescription(orderDetails.getDescription());

                newOrderDetail.setQuantity(orderDetails.getQuantity());

                newOrderDetail.setSubTotal(product.getSellPrice() * orderDetails.getQuantity());

                product.setAvailableQuantity(product.getAvailableQuantity() - orderDetails.getQuantity());
                products.add(productVariantRepository.save(product));
                totalPrice+= (product.getSellPrice() * orderDetails.getQuantity());
                newOrderDetail.setOrder(saveOrder);
                orderDetailsRepository.save(newOrderDetail);
            }
            customer.setPurchaseProducts(products);
            userRepository.save(customer);
            saveOrder.setTotalPrice(totalPrice);

        }

    return orderRepository.save(saveOrder);
    }


    public List<ProductDto> randomAllProducts(SearchDto searchDto) throws ResourceNotFoundException {

        Pageable pageable = PageRequest.of(searchDto.getPageNo(),searchDto.getPageSize());
        List<ProductDto> products = productVariantRepository.allProducts(pageable);
        for (ProductDto productDto:products) {
            ProductVariant product = productVariantRepository.findById(productDto.getId())
                    .orElseThrow(()-> new ResourceNotFoundException("Not Found"));

            List<String> productImagesURL = productImagesRepository.productImagesURLs(productDto.getId());
            productDto.setProductURLs(productImagesURL);
            User user = userRepository.findById(product.getSellerId())
                    .orElseThrow(()-> new ResourceNotFoundException("Seller Not Found"));
            productDto.setSellerName(user.getName());
        }
        return products;
    }

}
