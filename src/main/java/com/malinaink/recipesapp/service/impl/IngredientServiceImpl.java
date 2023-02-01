package com.malinaink.recipesapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malinaink.recipesapp.exception.FileUploadException;
import com.malinaink.recipesapp.exception.IngredientNotFoundException;
import com.malinaink.recipesapp.exception.ReadFileException;
import com.malinaink.recipesapp.model.Ingredient;
import com.malinaink.recipesapp.service.FilesService;
import com.malinaink.recipesapp.service.IngredientService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {


    @Value("${path.to.ingredients.data.file}")
    private String ingredientsDtaFile;

    @Value("${path.to.import.ingredients.data.file}")
    private String importIngredientsDataFile;

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

        if (!ingredients.containsKey(id)) {
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
            filesService.saveToFile(json, ingredientsDtaFile);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readFromFile() {
        String json = filesService.readFromFile(ingredientsDtaFile);
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File uploadIngredientsDatafile(@RequestParam MultipartFile file) throws FileUploadException {
        filesService.cleanToFile(importIngredientsDataFile);
        File dataFile = filesService.getDataFile(importIngredientsDataFile);
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
        } catch (IOException e) {
            throw new FileUploadException("Произошла ошибка при загрузке файла");
        }
        return dataFile;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }
}
