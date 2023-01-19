package com.malinaink.recipesapp.controllers;

import com.malinaink.recipesapp.model.Ingredient;
import com.malinaink.recipesapp.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
   private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/create")
    public Ingredient create(@RequestBody Ingredient ingredient) {
        return ingredientService.createIngredient(ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient read(@PathVariable long id) {
        return ingredientService.readIngredient(id);
    }

    @PutMapping("/{id}")
    public Ingredient update(@PathVariable long id, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public Ingredient delete(@PathVariable long id) {
        return ingredientService.deleteIngredient(id);
    }

    @GetMapping
    public Collection<Ingredient> readAllIngredient() {
        return ingredientService.readAllIngredient();
    }
}
