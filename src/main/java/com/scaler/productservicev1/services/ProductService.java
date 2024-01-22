package com.scaler.productservicev1.services;

import com.scaler.productservicev1.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<Product> getAllProducts() ;
    Product getSingleProduct(Long id) ;
    Product replaceProduct(Long id , Product product) ;

    Product addNewProduct(Product product);
}
