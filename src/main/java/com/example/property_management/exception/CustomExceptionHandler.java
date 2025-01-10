package com.example.property_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.logging.Logger;

@ControllerAdvice
public class CustomExceptionHandler {
    Logger logger = Logger.getLogger(getClass().getName());

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex){
        logger.info("BusinessException is throw");
        return new ResponseEntity<>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
