package com.apalyukha.restapi.controller;

import com.apalyukha.restapi.dto.CatDTO;
import com.apalyukha.restapi.entity.Cat;
import com.apalyukha.restapi.repository.CatRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {

    private final CatRepo catRepo;

    @Operation(
            summary = "Adds a new cat to the database",
            description = "Gets DTO cat and builder " +
                    "collects and stores the essence in the database"
    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        log.info(
                "New row " + catRepo.save(
                        Cat.builder()
                                .name(catDTO.getName())
                                .age(catDTO.getAge())
                                .weight(catDTO.getWeight())
                                .build())
        );
    }

    @Operation(
            summary = "List of all cats"
    )
    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepo.findAll();
    }

    @Operation(
            summary = "Search by ID"
    )
    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @Operation(
            summary = "Removing the cat from the database"
    )
    @DeleteMapping("/api")
    public void delCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @Operation(
            summary = "Changing the cat's characteristics"
    )
    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepo.save(cat).toString();
    }

}
