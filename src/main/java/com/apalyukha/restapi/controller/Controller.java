package com.apalyukha.restapi.controller;

import com.apalyukha.restapi.entity.Cat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/api/main")
    public String mainListener() {
        return "Hello, Java Spring";
    }

    @GetMapping("/api/cat")
    public String giveCat() {
        Cat cat = new Cat("bob", 2, 3);

        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(cat);
        } catch (JsonProcessingException e) {
            System.out.println("Error with cat");
        }
        return jsonData;
    }

    @PostMapping("/api/special")
    public String giveSpecialCat(@RequestParam String name) {
        Cat cat = new Cat(name, 2, 3);

        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(cat);
        } catch (JsonProcessingException e) {
            System.out.println("Error with cat");
        }
        return jsonData;
    }
}
