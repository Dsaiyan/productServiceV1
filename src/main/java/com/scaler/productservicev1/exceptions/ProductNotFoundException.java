package com.scaler.productservicev1.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message){
        super(message) ;
    }
}
