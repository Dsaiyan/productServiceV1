package com.scaler.productservicev1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//this class will have multiple methods
//will be serving at http request  "/hello"
//localhost:8080/hello
//

@RestController
@RequestMapping("/hello")
public class HelloController {


    @GetMapping("/say/{name}")
    public String sayHello( @PathVariable("name") String name){
        return "Hello There "+name ;
    }

}
