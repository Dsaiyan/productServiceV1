package com.scaler.productservicev1.repositories;

import com.scaler.productservicev1.models.Product;
import com.scaler.productservicev1.repositories.Projections.ProductInfo;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    //Get Single product

    Optional<Product> findById(Long id) ;

    //Get all products
    List<Product> findAll() ;

    //save new product
    Product save( Product product) ;

    //Delete By id
    @Override
    void deleteById(Long id);

    @Query(value = "select p.id as id ," +
            "p.title as title ," +
            "p.price as price from product p where p.id = :id",nativeQuery = true )
    List<ProductInfo> someQuery(@Param("id") Long id) ;
}
