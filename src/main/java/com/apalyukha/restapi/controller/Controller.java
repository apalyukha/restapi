package com.apalyukha.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/api/main")
    public String mainListener() {
        return "Hello, Java Spring";
    }
}
