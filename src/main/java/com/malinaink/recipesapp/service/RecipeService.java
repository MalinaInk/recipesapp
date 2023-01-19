package com.malinaink.recipesapp.service;

import com.malinaink.recipesapp.model.Ingredient;
import com.malinaink.recipesapp.model.Recipe;

import java.util.Map;

public interface RecipeService {

        Recipe createRecipe(Recipe recipe);

        Recipe readRecipe(long id);

}
