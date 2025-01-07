package com.example.property_management.service.impl;

import com.example.property_management.converter.PropertyConverter;
import com.example.property_management.dto.PropertyDTO;
import com.example.property_management.entity.PropertyEntity;
import com.example.property_management.repository.PropertyRepository;
import com.example.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.ConvertDTOtoEntity(propertyDTO);
        pe = propertyRepository.save(pe);

        PropertyDTO dto = propertyConverter.ConvertEntitytoDTO(pe);
        return dto;
    }

    @Override
    public List<PropertyDTO> GetAllProperties() {
        List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propertyList = new ArrayList<>();
        for (PropertyEntity pe : listOfProps ){
            PropertyDTO dto = propertyConverter.ConvertEntitytoDTO(pe);
            propertyList.add(dto);
        }
        return propertyList;
    }

    @Override
    public PropertyDTO UpdateProperty(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEntity.isPresent()){
            PropertyEntity pe = optEntity.get(); // Data from database
            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.ConvertEntitytoDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEntity.isPresent()){
            PropertyEntity pe = optEntity.get(); // Data from database
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.ConvertEntitytoDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEntity.isPresent() && !(optEntity.get().getPrice() == null)){
            PropertyEntity pe = optEntity.get(); // Data from database
            pe.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.ConvertEntitytoDTO(pe);
            propertyRepository.save(pe);
        }else {
           throw new IllegalStateException("La propiedad no existe o el precio es nulo");
        }
        return dto;
    }

    @Override
    public void DeleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
