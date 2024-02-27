package com.scaler.productservicev1.commons;

import com.scaler.productservicev1.DTOs.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationCommons {

    private RestTemplate restTemplate ;

    public AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate ;
    }

    public boolean validateToken(String token){
        ResponseEntity<UserDTO> response = restTemplate.postForEntity
                ("http://localhost:8181/users/validate/"+token ,
                        null , UserDTO.class) ;

        if(response.getBody() == null ){
            return false ;
        }
        return true ;
    }
}
