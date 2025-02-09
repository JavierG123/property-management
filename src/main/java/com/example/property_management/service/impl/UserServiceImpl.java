package com.example.property_management.service.impl;

import com.example.property_management.converter.UserConverter;
import com.example.property_management.dto.UserDTO;
import com.example.property_management.entity.AddressEntity;
import com.example.property_management.entity.UserEntity;
import com.example.property_management.exception.BusinessException;
import com.example.property_management.exception.ErrorModel;
import com.example.property_management.repository.AddressRepository;
import com.example.property_management.repository.UserRepository;
import com.example.property_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optUe = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if(optUe.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXIST");
            errorModel.setMessage("The Email already exist");
            errorModelList.add(errorModel);
            throw  new BusinessException(errorModelList);
        }
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setHouseNumber(userDTO.getHouseNumber());
        addressEntity.setCity(userDTO.getCity());
        addressEntity.setPostalCode(userDTO.getPostalCode());
        addressEntity.setStreet(userDTO.getStreet());
        addressEntity.setCountry(userDTO.getCountry());

        UserEntity userEntity = userConverter.convertDTOToEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        addressEntity.setUserEntity(userEntity);
        addressRepository.save(addressEntity);

        userDTO = userConverter.convertEntityToDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> userEntity;
        userEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        if(userEntity.isPresent()){
            userDTO = userConverter.convertEntityToDTO(userEntity.get());
        }else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect Email or Password");
            errorModelList.add(errorModel);

            throw  new BusinessException(errorModelList);
        }
        return userDTO;
    }
}
