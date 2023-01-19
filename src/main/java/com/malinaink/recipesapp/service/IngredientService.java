package com.malinaink.recipesapp.service;

import com.malinaink.recipesapp.model.Ingredient;

import java.util.Collection;
import java.util.Map;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredient);

    Ingredient readIngredient(long id);

    Ingredient updateIngredient(long id, Ingredient newIngredient);

    Ingredient deleteIngredient(long id);

    Collection<Ingredient> readAllIngredient();
}
