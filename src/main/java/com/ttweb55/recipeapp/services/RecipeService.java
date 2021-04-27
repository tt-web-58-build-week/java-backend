package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.models.Recipe;
import com.ttweb55.recipeapp.models.RecipeMinimum;
import com.ttweb55.recipeapp.models.User;

import java.util.List;

public interface RecipeService {
    // For Seed Data
    public Recipe save(Recipe recipe);

    // START - methods used by controller
    public Recipe createNewRecipe(RecipeMinimum recipe, User user);

    // END - methods used by controller

    public List<Recipe> findAllRecipesForCurrentUser(String username);
    public Recipe findRecipeById(Long id);
}
