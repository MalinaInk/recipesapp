package com.malinaink.recipesapp.controllers;

import com.malinaink.recipesapp.exception.FileDownloadException;
import com.malinaink.recipesapp.exception.FileUploadException;
import com.malinaink.recipesapp.service.FilesService;
import com.malinaink.recipesapp.service.IngredientService;
import com.malinaink.recipesapp.service.RecipeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class FilesController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    public FilesController(IngredientService ingredientService, RecipeService recipeService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }


    @GetMapping("recipes/export")
    @Tag(name = "Выгрузка рецептов", description = "Позволяет выгрузить добавленные рецепты в виде json-файла")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileDownloadException, FileNotFoundException {
        File file = recipeService.downloadDataFile();
            if (file.exists()) {
                return
                        ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .contentLength(file.length())
                                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"RecipesLog.json\"")
                                .body(new InputStreamResource(new FileInputStream(file)));
            } else throw new FileDownloadException("Произошла ошибка при скачивании файла");
    }

    @PostMapping(path = "recipes/import/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Tag(name = "Загрузка рецептов", description = "Позволяет загрузить рецепты в виде json-файла на диск")
    public ResponseEntity<Void> uploadRecipesDatafile(@RequestParam MultipartFile file) throws FileUploadException {
        File dataFile = recipeService.uploadRecipesDatafile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            throw new FileUploadException("Произошла ошибка при загрузке файла");
        }
    }

    @PostMapping(path = "ingredients/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Tag(name = "Загрузка ингредиентов", description = "Позволяет загрузить ингредиенты в виде json-файла на диск")
    public ResponseEntity<Void> uploadIngredientsDatafile(@RequestParam MultipartFile file) throws FileUploadException {
        File dataFile = ingredientService.uploadIngredientsDatafile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            throw new FileUploadException("Произошла ошибка при загрузке файла");
        }
    }
}

