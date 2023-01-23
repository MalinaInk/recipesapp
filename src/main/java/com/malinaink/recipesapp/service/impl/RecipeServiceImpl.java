package com.malinaink.recipesapp.service.impl;

import com.malinaink.recipesapp.exception.RecipeNotFoundException;
import com.malinaink.recipesapp.model.Recipe;
import com.malinaink.recipesapp.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private long idGenerator = 1;
    private final Map<Long, Recipe> recipes = new HashMap<>();

    @Override
    public Recipe createRecipe(Recipe recipe) {
        recipes.put(idGenerator++, recipe);
        return recipe;
    }

    @Override
    public Recipe readRecipe(long id) {
        if(!recipes.containsKey(id)){
            throw new RecipeNotFoundException(id);
        }
        return recipes.get(id);
    }

    @Override
    public Recipe updateRecipe(long id, Recipe recipe) {
        if (!recipes.containsKey(id)) {
            throw new RecipeNotFoundException(id);
        }
        return recipes.put(id,recipe);
    }

    @Override
    public Recipe deleteRecipe(long id) {
        if (!recipes.containsKey(id)) {
            throw new RecipeNotFoundException(id);
        }
        return recipes.remove(id);
    }

    @Override
    public Collection<Recipe> readAllRecipe() {
       return recipes.values();
    }
    }

