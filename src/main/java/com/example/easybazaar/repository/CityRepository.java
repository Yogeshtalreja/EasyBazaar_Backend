package com.example.easybazaar.repository;

import com.example.easybazaar.dto.AllCitiesDto;
import com.example.easybazaar.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSInput;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {


    @Query("select new com.example.easybazaar.dto.AllCitiesDto(city.id,city.name) from City city")
    List<AllCitiesDto> allCities();
}
