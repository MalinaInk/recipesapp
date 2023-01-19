package com.malinaink.recipesapp.controllers;

import com.malinaink.recipesapp.model.Recipe;
import com.malinaink.recipesapp.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/create")
    public Recipe create(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    @GetMapping("/{id}")
    public Recipe read(@PathVariable long id) {
        return recipeService.readRecipe(id);
    }

    @PutMapping("/{id}")
    public Recipe update(@PathVariable long id, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("/{id}")
    public Recipe delete(@PathVariable long id) {
        return recipeService.deleteRecipe(id);
    }

    @GetMapping
    public Collection<Recipe> readAllRecipes() {
        return recipeService.readAllRecipe();
    }
}
