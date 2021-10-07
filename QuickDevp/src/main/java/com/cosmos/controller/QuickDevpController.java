package com.cosmos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quick/test")
public class QuickDevpController {
    @GetMapping
    public String getHelloMessage(){
        return "Hello Abhijit";
    }
}
