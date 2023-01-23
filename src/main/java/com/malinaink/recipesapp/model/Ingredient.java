package com.malinaink.recipesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private String ingredientName;
    private int quantity;
    private String measure;
}

