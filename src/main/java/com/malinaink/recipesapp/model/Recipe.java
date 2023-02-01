package com.malinaink.recipesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String recipeName;
    private int cookingTime;
    private List <Ingredient> ingredients;
    private List <String> steps;
}
