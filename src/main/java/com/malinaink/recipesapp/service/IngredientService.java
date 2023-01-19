package com.malinaink.recipesapp.service;

import com.malinaink.recipesapp.model.Ingredient;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredient);

    Ingredient readIngredient(long id);
}
