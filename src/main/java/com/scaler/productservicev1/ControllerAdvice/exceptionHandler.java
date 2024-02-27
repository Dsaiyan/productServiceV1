package com.scaler.productservicev1.ControllerAdvice;

import com.scaler.productservicev1.DTOs.ExceptionDTO;
import com.scaler.productservicev1.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class exceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(
            ProductNotFoundException exception ){

        ExceptionDTO exceptionDTO = new ExceptionDTO() ;

        exceptionDTO.setMessage(exception.getMessage());
        exceptionDTO.setDetails("No product found with given ID , Please check it again");

        return new ResponseEntity<>(exceptionDTO, HttpStatus.OK) ;

    }
}
