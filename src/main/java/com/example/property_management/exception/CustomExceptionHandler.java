package com.example.property_management.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv){
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = null;
        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();

        for(FieldError fe: fieldErrorList){
            logger.debug("Inside field validation - level - debug : {} - {}",fe.getCode(), fe.getDefaultMessage());
            logger.info("Inside field validation - level - info: {} - {}",fe.getCode(), fe.getDefaultMessage());
            errorModel = new ErrorModel();
            errorModel.setCode(fe.getField());
            errorModel.setMessage(fe.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<>(errorModelList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex){
        for (ErrorModel em: bex.getErrors()){
            logger.debug("BusinessException is throw - level - debug : {} - {}",em.getCode(), em.getMessage());
            logger.info("BusinessException is throw - level - info: {} - {}",em.getCode(), em.getMessage());
            logger.warn("BusinessException is throw - level - warn: {} - {}",em.getCode(), em.getMessage());
            logger.error("BusinessException is throw - level - error: {} - {}",em.getCode(), em.getMessage());
        }
        return new ResponseEntity<>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }

}
