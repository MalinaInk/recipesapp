package com.malinaink.recipesapp.controllers;

import com.malinaink.recipesapp.model.Recipe;
import com.malinaink.recipesapp.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "CRUD-операции и другие эндпойнты для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/create")
    @Operation(summary = "Создание рецепта", description = "Принимает заполненные поля, создает объект, присваивает ему уникальный идентификатор")
    public Recipe create(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение рецепта", description = "Находит и выдает рецепт по уникальному идентификатору")
    public Recipe read(@PathVariable long id) {
        return recipeService.readRecipe(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование рецепта", description = "Меняет поля рецепта, найденного по введенному уникальному идентификатору")
    public Recipe update(@PathVariable long id, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта", description = "Удаляет рецепт, найденный по введенному уникальному идентификатору")
    public Recipe delete(@PathVariable long id) {
        return recipeService.deleteRecipe(id);
    }

    @GetMapping("/")
    @Operation(summary = "Получение списка рецептов", description = "Выводит весь перечень добавленных рецептов в виде массива объектов")
    public Collection<Recipe> readAllRecipes() {
        return recipeService.readAllRecipe();
    }
}
