package com.malinaink.recipesapp.controllers;

import com.malinaink.recipesapp.model.Ingredient;
import com.malinaink.recipesapp.service.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
   private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Ingredient create(@RequestBody Ingredient ingredient) {
        return ingredientService.createIngredient(ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient read(@PathVariable long id) {
        return ingredientService.readIngredient(id);
    }
}
