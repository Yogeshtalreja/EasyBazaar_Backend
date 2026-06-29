package com.example.easybazaar.mappers;

import com.example.easybazaar.dto.ShipperDto;
import com.example.easybazaar.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ShipperMapper {

    User shipperDtoToUser(ShipperDto shipperDto);

}
