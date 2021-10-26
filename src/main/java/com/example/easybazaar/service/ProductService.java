package com.example.easybazaar.service;

import com.example.easybazaar.dto.OrderDetailsDto;
import com.example.easybazaar.dto.OrderDto;
import com.example.easybazaar.enums.OrderStatusEnum;
import com.example.easybazaar.exceptions.ResourceNotFoundException;
import com.example.easybazaar.model.Order;
import com.example.easybazaar.model.OrderDetails;
import com.example.easybazaar.model.ProductVariant;
import com.example.easybazaar.model.User;
import com.example.easybazaar.repository.OrderDetailsRepository;
import com.example.easybazaar.repository.OrderRepository;
import com.example.easybazaar.repository.ProductVariantRepository;
import com.example.easybazaar.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {
    private final UserRepository userRepository;
    private final ProductVariantRepository productVariantRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;

    @Transactional(rollbackOn = {ResourceNotFoundException.class , Exception.class})
    public Order productPurchase(OrderDto orderDto) throws ResourceNotFoundException {
        if (orderDto.getCustomerId()==null)
            throw new ResourceNotFoundException("Customer ID is Missing");

        Long totalPrice = 0L;

        User customer = userRepository.findByIdAndUserTypeAndIsActive(orderDto.getCustomerId(), "CUSTOMER",true);
        if (customer == null)
            throw new ResourceNotFoundException("CUSTOMER With ID "+orderDto.getCustomerId()+" Not Found");

        Order newOrder = new Order();
        newOrder.setOrderId(UUID.randomUUID().toString());
        newOrder.setCreatedDate(LocalDate.now());
        newOrder.setStatus(OrderStatusEnum.IN_PROGRESS);
        newOrder.setDeliveredDate(LocalDate.now().plusDays(2));

        Order saveOrder = orderRepository.save(newOrder);


        if (orderDto.getOrderDetails()!=null){
            List<ProductVariant> products = new ArrayList<>();
            for (OrderDetailsDto orderDetails: orderDto.getOrderDetails()) {
                if (orderDetails.getProductId()==null)
                    throw new ResourceNotFoundException("Product ID is Missing");

                ProductVariant product = productVariantRepository.findById(orderDetails.getProductId())
                        .orElseThrow(()-> new ResourceNotFoundException("Product with ID "+orderDetails.getProductId()+" not Found"));
                if (product.getAvailableQuantity() < orderDetails.getQuantity())
                    throw new ResourceNotFoundException(product.getName()+" has only "+product.getAvailableQuantity()+" Quantity");

                OrderDetails newOrderDetail = new OrderDetails();
                newOrderDetail.setProduct(product);
                newOrderDetail.setDescription(orderDetails.getDescription());
                newOrderDetail.setQuantity(orderDetails.getQuantity());

                newOrderDetail.setSubTotal(product.getSellPrice() * orderDetails.getQuantity());
                products.add(product);
                product.setAvailableQuantity(product.getAvailableQuantity() - orderDetails.getQuantity());
                productVariantRepository.save(product);
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

}
