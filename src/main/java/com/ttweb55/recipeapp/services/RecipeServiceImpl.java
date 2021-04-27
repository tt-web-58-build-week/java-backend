package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.models.Recipe;
import com.ttweb55.recipeapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
