package com.scaler.productservicev1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name ;
    @OneToMany(mappedBy = "category" ,fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE ) // being already mapped by another attribute called "category" in product class
    private List<Product> productList ;
    private String description ;

}
