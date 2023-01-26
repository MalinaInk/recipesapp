package com.malinaink.recipesapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malinaink.recipesapp.exception.RecipeNotFoundException;
import com.malinaink.recipesapp.model.Recipe;
import com.malinaink.recipesapp.service.FilesService;
import com.malinaink.recipesapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    final private FilesService filesService;

    private long idGenerator = 1;
    private HashMap<Long, Recipe> recipes = new HashMap<>();
    @Value("${path.to.data.file1}")
    private String dataFilePath1;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        recipes.put(idGenerator++, recipe);
        saveToFile();
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
        recipes.put(id,recipe);
        saveToFile();
        return recipe;
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

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            filesService.saveToFile(json, dataFilePath1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = filesService.readFromFile(dataFilePath1);
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }
}

