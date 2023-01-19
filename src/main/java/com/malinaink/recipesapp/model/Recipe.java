package com.malinaink.recipesapp.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Recipe {
    private String recipeName;
    private int cookingTime;
    private final List <Ingredient> ingredients = new ArrayList<>();
    private final List <String> steps = new ArrayList<>();

    public Recipe(String recipeName, int cookingTime) {
        this.recipeName = recipeName;
        this.cookingTime = cookingTime;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }
}
