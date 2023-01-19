package com.malinaink.recipesapp.service.impl;

import com.malinaink.recipesapp.exception.IngredientNotFoundException;
import com.malinaink.recipesapp.model.Ingredient;
import com.malinaink.recipesapp.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Long, Ingredient> ingredients = new HashMap<>();
    private long idGenerator = 1;

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredients.put(idGenerator++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient readIngredient(long id) {
        if(!ingredients.containsKey(id)){
            throw new IngredientNotFoundException(id);
        }
        return ingredients.get(id);
    }
}
