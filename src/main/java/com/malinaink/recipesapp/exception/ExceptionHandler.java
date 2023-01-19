package com.malinaink.recipesapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<String> handleIngredientNotFoundException (IngredientNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Ингредиент с id = %id не найден!", e.getId()));
    }
}
