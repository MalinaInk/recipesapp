package com.malinaink.recipesapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malinaink.recipesapp.exception.IngredientNotFoundException;
import com.malinaink.recipesapp.model.Ingredient;
import com.malinaink.recipesapp.model.Recipe;
import com.malinaink.recipesapp.service.FilesService;
import com.malinaink.recipesapp.service.IngredientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Value("${path.to.data.file2}")
    private String dataFilePath2;

    final private FilesService filesService;
    private HashMap<Long, Ingredient> ingredients = new HashMap<>();
    private long idGenerator = 1;


    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredients.put(idGenerator++, ingredient);
        saveToFile();
        return ingredient;
    }

    @Override
    public Ingredient readIngredient(long id) {

        if(!ingredients.containsKey(id)) {
            throw new IngredientNotFoundException(id);
        }
        return ingredients.get(id);
    }
    @Override
    public Ingredient updateIngredient(long id, Ingredient ingredient) {

        if (!ingredients.containsKey(id)) {
            throw new IngredientNotFoundException(id);
        }
         ingredients.put(id, ingredient);
         saveToFile();
         return ingredient;
    }

    @Override
    public Ingredient deleteIngredient(long id) {
        if (!ingredients.containsKey(id)) {
            throw new IngredientNotFoundException(id);
        }
        return ingredients.remove(id);
    }

    @Override
    public Collection<Ingredient> readAllIngredient() {
        return ingredients.values();
    }
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            filesService.saveToFile(json, dataFilePath2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = filesService.readFromFile(dataFilePath2);
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
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
