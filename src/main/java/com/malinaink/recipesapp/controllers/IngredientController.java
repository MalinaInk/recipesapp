package com.malinaink.recipesapp.controllers;

import com.malinaink.recipesapp.model.Ingredient;
import com.malinaink.recipesapp.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "CRUD-операции и другие эндпойнты для работы с ингредиентами")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(summary = "Создание ингредиента", description = "Принимает заполненные поля, создает объект, присваивает ему уникальный идентификатор")
    public Ingredient create(@RequestBody Ingredient ingredient) {
        return ingredientService.createIngredient(ingredient);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение ингредиента", description = "Находит и выдает ингредиент по уникальному идентификатору")
    public Ingredient read(@PathVariable long id) {
        return ingredientService.readIngredient(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование ингредиента", description = "Меняет поля ингредиента, найденного по введенному уникальному идентификатору")
    public Ingredient update(@PathVariable long id, @RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента", description = "Удаляет ингредиент, найденный по введенному уникальному идентификатору")
    public Ingredient delete(@PathVariable long id) {
        return ingredientService.deleteIngredient(id);
    }

    @GetMapping("/")
    @Operation(summary = "Получение списка ингредиентов", description = "Выводит весь перечень добавленных ингредиентов в виде массива объектов")
    public Collection<Ingredient> readAllIngredient() {
        return ingredientService.readAllIngredient();
    }
}
