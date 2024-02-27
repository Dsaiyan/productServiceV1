package com.scaler.productservicev1.services;

import com.scaler.productservicev1.exceptions.ProductNotFoundException;
import com.scaler.productservicev1.models.Category;
import com.scaler.productservicev1.models.Product;
import com.scaler.productservicev1.repositories.CategoryRepository;
import com.scaler.productservicev1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("ProductService_01")
public class selfService implements ProductService{
    private ProductRepository productRepository ;
    private CategoryRepository categoryRepository ;

    @Autowired
    public selfService(ProductRepository productRepository,
                       CategoryRepository categoryRepository){
        this.productRepository = productRepository ;
        this.categoryRepository = categoryRepository ;
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {

        Optional<Product> productOptional = productRepository.findById(id) ;

        if( productOptional.isEmpty() ){
            throw new ProductNotFoundException("Product with "+id+" is not exits") ;
        }
        Product product = productOptional.get() ;

        return product;
    }

    @Override
    public Product addNewProduct(Product product) {
        //now we can add new product without creating category fast because of CascadeType.persist
        //added in the product

//        Optional<Category> optionalCategory =
//               categoryRepository.findByName(product.getCategory().getName()) ;
//
//      //not present then save a category
//        if( optionalCategory.isEmpty() ){
//            Category savedCategory =
//                    categoryRepository.save(product.getCategory()) ;
//            product.setCategory(savedCategory);
//        }
//        else{  //if present then set a category
//
//            product.setCategory(optionalCategory.get());
//        }

        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product)
            throws ProductNotFoundException{
        Optional<Product> optionalProduct = productRepository.findById(id) ;

        if(optionalProduct.isEmpty() ){
            throw new ProductNotFoundException(
                    "Product with id: "+id+" is not exist. ");
        }
        Product saveProduct = optionalProduct.get();

        //Change all attributes of saved product
        saveProduct.setTitle(product.getTitle());
        saveProduct.setDescription(product.getDescription());
        saveProduct.setPrice(product.getPrice());
        saveProduct.setImageURL(product.getImageURL());

        Optional<Category> optionalCategory = categoryRepository.findByName(
                product.getCategory().getName() ) ;

        if(optionalCategory.isPresent()){
            saveProduct.setCategory(optionalCategory.get());
        }
        else{
            saveProduct.setCategory(
                    categoryRepository.save(product.getCategory())
            );
        }

        return productRepository.save(saveProduct);
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Optional<Product> optionalProduct = productRepository.findById(id) ;

        if( optionalProduct.isEmpty() ){
            throw new ProductNotFoundException(
                    "Product with id: "+id+" is not exist.") ;
        }
        Product savedProduct = optionalProduct.get();

        //change only the given attributes of saved product
        if( product.getTitle() != null ){
            savedProduct.setTitle(product.getTitle());
        }
        if( product.getDescription() != null ){
            savedProduct.setDescription(product.getDescription());
        }
        if( product.getPrice() != null ){
            savedProduct.setPrice(product.getPrice());
        }

        if( product.getCategory() != null){
            Optional<Category> optionalCategory =
                    categoryRepository.findByName(product.getCategory()
                            .getName()) ;

            if( optionalCategory.isPresent() ){
                savedProduct.setCategory( optionalCategory.get() );
            }

            else{
                savedProduct.setCategory(
                        categoryRepository.save(product.getCategory()) ) ;
            }
        }

        if( product.getImageURL() != null ){
            savedProduct.setImageURL(product.getImageURL());
        }

        return productRepository.save(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException{

        Optional<Product> optionalProduct = productRepository.findById(id) ;

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(
                    "Product with id: "+id+" is not exist.") ;
        }
        else{
            productRepository.deleteById(id);
        }
    }
}
