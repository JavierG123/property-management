package com.example.property_management.repository;

import com.example.property_management.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long>{
    List<PropertyEntity> findAllByUserEntityId(Long userId);
}
