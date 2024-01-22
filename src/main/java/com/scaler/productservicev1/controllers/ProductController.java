package com.scaler.productservicev1.controllers;

import com.scaler.productservicev1.models.Product;
import com.scaler.productservicev1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController     //to tell spring this is a specialClass which will have some methods called via Apis
@RequestMapping("/products")    // and this will be the path
public class ProductController {
    ProductService productService ;

    @Autowired
    public ProductController(ProductService productService){

        this.productService = productService ;
    }

    // using 3rd party (Fake Store APIs)

    @GetMapping()
    public ResponseEntity< List<Product> > getAllProducts(){

        ResponseEntity<List<Product>> response = new ResponseEntity<>(
                productService.getAllProducts(), HttpStatus.FOUND
        ) ;

        return response ;
    }


    @GetMapping("/{id}")
    public Product getSingleProductById(@PathVariable("id") Long id){
        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product){
        return productService.addNewProduct(product) ;
    }


    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id , @RequestBody Product product ){
        return new Product() ;
    }


    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id")Long id , @RequestBody Product product){

        return productService.replaceProduct(id , product ) ;
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        return;
    }


}
