package com.malinaink.recipesapp.controllers;

import com.malinaink.recipesapp.model.Recipe;
import com.malinaink.recipesapp.service.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public Recipe create(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    @GetMapping("/{id}")
    public Recipe read(@PathVariable long id) {
        return recipeService.readRecipe(id);
    }
}
