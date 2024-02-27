package com.scaler.productservicev1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

@Getter
@Setter
@Entity
public class  Product extends BaseModel{
    private String title ;
    @Column(length = 700)
    private String description ;
    private Double price ;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER
            ,cascade = {CascadeType.PERSIST,CascadeType.MERGE})

    private Category category ;
    private String imageURL ;
}
