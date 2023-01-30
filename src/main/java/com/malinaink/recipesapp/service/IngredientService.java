package com.malinaink.recipesapp.service;

import com.malinaink.recipesapp.model.Ingredient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collection;
import java.util.Map;

public interface IngredientService {
    Ingredient createIngredient(Ingredient ingredient);

    Ingredient readIngredient(long id);

    Ingredient updateIngredient(long id, Ingredient ingredient);

    Ingredient deleteIngredient(long id);

    Collection<Ingredient> readAllIngredient();

    void readFromFile();

    File uploadIngredientsDatafile();
}
