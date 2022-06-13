package com.example.easybazaar;

import com.example.easybazaar.dto.CityDto;
import com.example.easybazaar.service.CityService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.model.core.TypeRef;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


//@EnableEurekaClient
//@EnableFeignClients
@SpringBootApplication
public class EasyBazaarApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyBazaarApplication.class, args);
    }


//    @Bean
//    CommandLineRunner runner(CityService service){
//        return args -> {
//            ObjectMapper mapper = new ObjectMapper();
//            TypeReference<List<CityDto>> typeReference = new TypeReference<List<CityDto>>() {};
//            InputStream inputStream = TypeReference.class.getClassLoader().getResourceAsStream("cities.json");
////            URL src = "D:\\cities.json";
////              try{
//                  List<CityDto> cityDtos = mapper.readValue(inputStream,typeReference);
//                  for (CityDto city:cityDtos) {
//                      service.save(city);
//                  }
////
////              }catch (Exception e){
////                  System.out.println("Unable to Save User "+e.getMessage());
////              }
//        };
//    }
}
