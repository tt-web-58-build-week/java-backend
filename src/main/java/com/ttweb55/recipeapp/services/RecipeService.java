package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.models.Recipe;
import com.ttweb55.recipeapp.models.RecipeCategory;
import com.ttweb55.recipeapp.models.RecipeMinimum;
import com.ttweb55.recipeapp.models.User;

import java.util.List;
import java.util.Set;

public interface RecipeService {
    // For Seed Data
    public Recipe save(Recipe recipe);

    // START - methods used by controller
    public Recipe createNewRecipe(RecipeMinimum recipe, User user);
    public Recipe updateRecipe(RecipeMinimum recipe, long id);
    void deleteRecipe(long id);
    // END - methods used by controller

    public List<Recipe> findAllRecipesForCurrentUser(String username);
    public Set<RecipeCategory> findAllRecipesByCategoryId(Long categoryid, String title);
    public Recipe findRecipeById(Long id);
}
