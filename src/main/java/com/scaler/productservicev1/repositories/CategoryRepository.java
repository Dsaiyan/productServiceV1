package com.scaler.productservicev1.repositories;

import com.scaler.productservicev1.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long > {

    //save Category
    Category save(Category category) ;

    //find category
    Optional<Category> findByName(String name) ;
}
