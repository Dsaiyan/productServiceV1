package com.scaler.productservicev1.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String email;
    private String hashedPassword ;
    private String userName ;
    private List<Role> roles ;
    private boolean isVerified ;
}
