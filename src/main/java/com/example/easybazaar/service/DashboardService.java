package com.example.easybazaar.service;

import com.example.easybazaar.dto.TotalSellersCustomerDto;
import com.example.easybazaar.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;

    public TotalSellersCustomerDto totalSellersCustomerDto(){

        return new TotalSellersCustomerDto(userRepository.totalSellers(), userRepository.totalCustomers(), userRepository.totalShippers());
    }
}
