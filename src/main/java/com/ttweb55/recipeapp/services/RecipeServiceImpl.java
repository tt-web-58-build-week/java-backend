package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.models.Recipe;
import com.ttweb55.recipeapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> findAllRecipesForCurrentUser(String username) {
        List<Recipe> recipes = new ArrayList<>();

        recipeRepository.findCurrentUserRecipes(username)
                .iterator()
                .forEachRemaining(recipes::add);

        return recipes;
    }
}
