package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class DefaultController {

    @GetMapping(path = "/", produces = "application/json")
    public String index() {
        return "index";
    }
}