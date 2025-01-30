package com.example.property_management.repository;

import com.example.property_management.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

    Optional<UserEntity> findByOwnerEmailAndPassword(String email, String password);
    // Using JPA findBy you can type directly what you want. In this example email and password. Those words need to match the variables in the Entity
    Optional<UserEntity> findByOwnerEmail(String email);
    // findByEmail

}
