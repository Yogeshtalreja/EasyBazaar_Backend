package com.example.easybazaar.service;

import com.example.easybazaar.dto.AllCitiesDto;
import com.example.easybazaar.dto.CityDto;
import com.example.easybazaar.model.City;
import com.example.easybazaar.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository repository;

    public List<AllCitiesDto> allCities(){

        return repository.allCities();
    }

}
