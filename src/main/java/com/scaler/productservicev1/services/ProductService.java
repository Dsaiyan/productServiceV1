package com.scaler.productservicev1.services;

import com.scaler.productservicev1.models.Product;

import java.util.List;


public interface ProductService {
    List<Product> getAllProducts() ;
    Product getSingleProduct(Long id) ;
    Product addNewProduct(Product product);
    Product replaceProduct(Long id , Product product) ;
    Product updateProduct(Long id , Product product);
    void deleteProduct(Long id) ;
}
