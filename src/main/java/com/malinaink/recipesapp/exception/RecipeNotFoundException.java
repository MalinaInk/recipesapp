package com.malinaink.recipesapp.exception;

public class RecipeNotFoundException  extends RuntimeException{
    private final long id;

    public RecipeNotFoundException(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
