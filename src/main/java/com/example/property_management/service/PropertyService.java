package com.example.property_management.service;

import com.example.property_management.dto.PropertyDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PropertyService {

    PropertyDTO saveProperty(PropertyDTO propertyDTO);
    List<PropertyDTO> getAllProperties();
    List<PropertyDTO> getAllPropertiesForUser(Long userId);
    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);
    PropertyDTO updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, Long propertyId);
    PropertyDTO updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, Long propertyId);
    void deleteProperty(Long propertyId);

}
