package com.example.property_management.service.impl;

import com.example.property_management.converter.PropertyConverter;
import com.example.property_management.dto.PropertyDTO;
import com.example.property_management.entity.PropertyEntity;
import com.example.property_management.entity.UserEntity;
import com.example.property_management.exception.BusinessException;
import com.example.property_management.exception.ErrorModel;
import com.example.property_management.repository.PropertyRepository;
import com.example.property_management.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        Optional<UserEntity> optionalUserEntity = userRepository.findById(propertyDTO.getUserId());
        if(optionalUserEntity.isPresent()){
            PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
            pe.setUserEntity(optionalUserEntity.get());
            pe = propertyRepository.save(pe);
            propertyDTO = propertyConverter.convertEntitytoDTO(pe);
        }else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_ID_NOT_EXIST");
            errorModel.setMessage("User does not exist");
            errorModelList.add(errorModel);

            throw  new BusinessException(errorModelList);
        }

        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propertyList = new ArrayList<>();
        for (PropertyEntity pe : listOfProps ){
            PropertyDTO dto = propertyConverter.convertEntitytoDTO(pe);
            propertyList.add(dto);
        }
        return propertyList;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> listOfProps = propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDTO> propertyList = new ArrayList<>();
        for (PropertyEntity pe : listOfProps ){
            PropertyDTO dto = propertyConverter.convertEntitytoDTO(pe);
            propertyList.add(dto);
        }
        return propertyList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEntity.isPresent()){
            PropertyEntity pe = optEntity.get(); // Data from database
            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntitytoDTO(pe);
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
            dto = propertyConverter.convertEntitytoDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optEntity.isPresent() && propertyDTO.getPrice() != null){
            PropertyEntity pe = optEntity.get(); // Data from database
            pe.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.convertEntitytoDTO(pe);
            propertyRepository.save(pe);
        }else {
           throw new IllegalStateException("La propiedad no existe o el precio es nulo");
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
