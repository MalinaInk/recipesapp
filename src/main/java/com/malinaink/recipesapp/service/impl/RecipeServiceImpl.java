package com.malinaink.recipesapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malinaink.recipesapp.exception.FileDownloadException;
import com.malinaink.recipesapp.exception.FileUploadException;
import com.malinaink.recipesapp.exception.RecipeNotFoundException;
import com.malinaink.recipesapp.model.Recipe;
import com.malinaink.recipesapp.service.FilesService;
import com.malinaink.recipesapp.service.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    final private FilesService filesService;

    private long idGenerator = 1;
    private HashMap<Long, Recipe> recipes = new HashMap<>();


    @Value("${path.to.recipes.data.file}")
    private String recipesDataFile;

    @Value("${path.to.import.recipes.data.file}")
    private String importRecipesDataFile;

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
            filesService.saveToFile(json, recipesDataFile);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = filesService.readFromFile(recipesDataFile);
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File downloadDataFile() {
        return filesService.getDataFile(recipesDataFile);
        }
    @Override
    public File uploadRecipesDatafile(@RequestParam MultipartFile file) throws FileUploadException {
        filesService.cleanToFile(importRecipesDataFile);
        File dataFile = filesService.getDataFile(importRecipesDataFile);
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

