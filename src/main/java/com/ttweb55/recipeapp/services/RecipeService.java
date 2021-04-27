package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.models.Recipe;

import java.util.List;

public interface RecipeService {
    public Recipe save(Recipe recipe);

    public List<Recipe> findAllRecipesForCurrentUser(String username);
}
