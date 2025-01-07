package com.example.property_management.converter;

import com.example.property_management.dto.PropertyDTO;
import com.example.property_management.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity ConvertDTOtoEntity(PropertyDTO propertyDTO){

        PropertyEntity pe = new PropertyEntity();
        pe.setTitle(propertyDTO.getTitle());
        pe.setAddress(propertyDTO.getAddress());
        pe.setOwnerEmail(propertyDTO.getOwnerEmail());
        pe.setOwnerName(propertyDTO.getOwnerName());
        pe.setPrice(propertyDTO.getPrice());
        pe.setDescription(propertyDTO.getDescription());

        return pe;
    }

    public PropertyDTO ConvertEntitytoDTO(PropertyEntity propertyEntity){

        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(propertyEntity.getId());
        propertyDTO.setTitle(propertyEntity.getTitle());
        propertyDTO.setAddress(propertyEntity.getAddress());
        propertyDTO.setOwnerEmail(propertyEntity.getOwnerEmail());
        propertyDTO.setOwnerName(propertyEntity.getOwnerName());
        propertyDTO.setPrice(propertyEntity.getPrice());
        propertyDTO.setDescription(propertyEntity.getDescription());
        return propertyDTO;
    }
}
