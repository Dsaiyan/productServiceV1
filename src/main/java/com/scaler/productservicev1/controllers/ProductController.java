package com.scaler.productservicev1.controllers;

import com.scaler.productservicev1.commons.AuthenticationCommons;
import com.scaler.productservicev1.models.Product;
import com.scaler.productservicev1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController     //to tell spring this is a specialClass which will have some methods called via Apis
@RequestMapping("/products")    // and this will be the path
public class ProductController {
    private ProductService productService ;
    private RestTemplate restTemplate ;
    private AuthenticationCommons authenticationCommons ;


    @Autowired
    public ProductController(@Qualifier("ProductService_01") ProductService productService ,
                             RestTemplate restTemplate,
                             AuthenticationCommons authenticationCommons){

        this.productService = productService ;
        this.restTemplate = restTemplate ;
        this.authenticationCommons = authenticationCommons ;
    }

    // using 3rd party (Fake Store APIs)

    @GetMapping()
    public ResponseEntity< List<Product> > getAllProducts(){
//
//        if( ! authenticationCommons.validateToken(token) ){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN) ;
//        }

        ResponseEntity<List<Product>> response = new ResponseEntity<>(
                productService.getAllProducts(), HttpStatus.OK ) ;

        return response ;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                productService.getSingleProduct(id) ,
                HttpStatus.OK ) ;
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product){
        return new ResponseEntity<>(
                productService.addNewProduct(product) ,
                HttpStatus.CREATED) ;
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id ,
                                 @RequestBody Product product ){

        return new ResponseEntity<>(productService.updateProduct(id,product),
                HttpStatus.ACCEPTED) ;
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id")Long id ,
                                                  @RequestBody Product product){

        return new ResponseEntity<>(productService.replaceProduct(id , product ),
                HttpStatus.ACCEPTED) ;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {

        productService.deleteProduct(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
