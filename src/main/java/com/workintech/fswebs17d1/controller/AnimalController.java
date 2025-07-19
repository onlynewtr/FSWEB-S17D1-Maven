package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/workintech/animal")
public class AnimalController {

    private Map<Integer, Animal> animals;

    @Value("${project.developer.fullname}")
    private String fullName;
    @Value("${course.name}")
    private String courseName;


    @PostConstruct
    public void loadAll() {
        System.out.println("postconstruct çalıştı.");
        this.animals = new HashMap<>();
        this.animals.put(1, new Animal(1, "maymun"));
        System.out.println("animalsMap = " + animals);
        System.out.println("fullName=" + fullName + "  --   " + "courseName=" + courseName);
    }

    @GetMapping
    public List<Animal> getAnimals() {
        System.out.println("---- animals get all triggered!!");
        return new ArrayList<>(this.animals.values());
    }

    @GetMapping("{id}")
    public Animal getAnimal(@PathVariable("id") int id) {
        System.out.println("---- get animal by id triggered");
        return this.animals.get(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        System.out.println("--- post animal  triggered");
        this.animals.put(animal.getId(), animal);
    }

    @PutMapping("{id}")
    public Animal updateAnimal(@PathVariable("id") int id, @RequestBody Animal newAnimal) {
        System.out.println("--- put animal triggered!");
        this.animals.replace(id, newAnimal);
        return this.animals.get(id);
    }

    @DeleteMapping("{id}")
    public void deleteAnimal(@PathVariable("id") int id) {
        System.out.println("---- delete triggered!");
        this.animals.remove(id);
    }


}