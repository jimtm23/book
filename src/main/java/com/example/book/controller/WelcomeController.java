package com.example.book.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class WelcomeController {

    @RequestMapping("/")
    public String welcome() {
        return "<h1>Hello</h1>";
    }

}
