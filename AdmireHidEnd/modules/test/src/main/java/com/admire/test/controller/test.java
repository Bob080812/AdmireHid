package com.admire.test.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class test {
    @RequestMapping("/say")
    public String sayHello(){
        return "Hello World";
    }
}
