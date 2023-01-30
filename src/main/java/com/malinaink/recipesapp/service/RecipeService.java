package com.malinaink.recipesapp.service;

import com.malinaink.recipesapp.exception.FileDownloadException;
import com.malinaink.recipesapp.model.Ingredient;
import com.malinaink.recipesapp.model.Recipe;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Map;

public interface RecipeService {

        Recipe createRecipe(Recipe recipe);

        Recipe readRecipe(long id);

        Recipe updateRecipe(long id, Recipe recipe);

        Recipe deleteRecipe(long id);

        Collection<Recipe> readAllRecipe();

    File downloadDataFile() ;

    File uploadRecipesDatafile();
}
