package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {
    private Map<Integer, Animal> animals = new HashMap<>();

    @GetMapping
    public List<Animal> getAllAnimals(){
        return animals.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable("id") int id){
        if (!animals.containsKey(id))
            return new Animal();
        return animals.get(id);
    }

    @PostMapping
    public Animal saveAnimal(@RequestBody Animal animal){
        animals.put(animal.getId(), animal);
        return animal;
    }

    @PutMapping("{id}")
    public Animal updateAnimal(@PathVariable Integer id, @RequestBody Animal updatedAnimal){
        if (animals.containsKey(id)){
            updatedAnimal.setId(id);
            animals.put(id, updatedAnimal);
            return updatedAnimal;
        } else {
            throw new RuntimeException("Animal with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/{id}")
    public String removeAnimal(@PathVariable("id") Integer id){
        Animal removedAnimal = animals.remove(id);
        return id + "id numaralı " + removedAnimal + " silinmiştir.";
    }
}
