package com.malinaink.recipesapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerClass {
    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<String> handleIngredientNotFoundException(IngredientNotFoundException e) {
        log.error(e.getMessage(), e);
        System.out.println("Нет запрашиваемого ингредиента в мапе");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Ингредиент с id = %d не найден!", e.getId()));
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<String> handleRecipeNotFoundException(RecipeNotFoundException e) {
        log.error(e.getMessage(), e);
        System.out.println("Нет запрашиваемого рецептата в мапе");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Рецепт с id = %d не найден!", e.getId()));
    }

    @ExceptionHandler(ReadFileException.class)
    public ResponseEntity<String> handleFileNotFoundException(ReadFileException e) {
        log.error(e.getMessage(), e);
//      System.err.println();
        System.out.println("Файл по такому пути не существует");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Не удалось открыть файл!");
    }

    @ExceptionHandler(FileDownloadException.class)
    public ResponseEntity<String> handleFileDownloadException(Exception e) {
        log.error(e.getMessage(), e);
        System.out.println("Ошибка загрузки файла");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Не удалось скачать файл!");
    }

    @ExceptionHandler({FileUploadException.class, FileNotFoundException.class})
    public ResponseEntity<String> handleFileUploadException(Exception e) {
        log.error(e.getMessage(), e);
        System.out.println("Ошибка скачивания файла");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Не удалось загрузить файл!");
    }

    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<String> handleEmptyFileException(Exception e) {
        log.error(e.getMessage(), e);
        System.out.println("Данные в файле отсутствуют");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
