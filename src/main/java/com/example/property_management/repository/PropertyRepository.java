package com.example.property_management.repository;

import com.example.property_management.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long>{

}
