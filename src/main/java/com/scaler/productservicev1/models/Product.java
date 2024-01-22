package com.scaler.productservicev1.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class  Product extends BaseModel{
    private String title ;
    private String description ;
    private double price ;
    @ManyToOne
    private Category category ;
    private String imageURL ;
}
