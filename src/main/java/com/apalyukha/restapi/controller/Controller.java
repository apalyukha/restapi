package com.apalyukha.restapi.controller;

import com.apalyukha.restapi.entity.Cat;
import com.apalyukha.restapi.repository.CatRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {

    private final ObjectMapper objectMapper;
    private final CatRepo catRepo;

//    @GetMapping("/api/main")
//    public String mainListener() {
//        return "Hello, Java Spring";
//    }
//
//    @GetMapping("/api/cat")
//    public String giveCat() {
//        Cat cat = new Cat("bob", 2, 3);
//
//        String jsonData = null;
//        try {
//            jsonData = objectMapper.writeValueAsString(cat);
//        } catch (JsonProcessingException e) {
//            System.out.println("Error with cat");
//        }
//        return jsonData;
//    }
//
//    @PostMapping("/api/special")
//    public String giveSpecialCat(@RequestParam String name) {
//        Cat cat = new Cat(name, 2, 3);
//
//        String jsonData = null;
//        try {
//            jsonData = objectMapper.writeValueAsString(cat);
//        } catch (JsonProcessingException e) {
//            System.out.println("Error with cat");
//        }
//        return jsonData;
//    }

    @PostMapping("/api/add")
    public void addCat(@RequestBody Cat cat) {
        log.info("New row " + catRepo.save(cat));
    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepo.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void delCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepo.save(cat).toString();
    }

}
