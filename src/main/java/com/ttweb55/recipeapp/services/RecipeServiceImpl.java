package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.exceptions.ResourceNotFoundException;
import com.ttweb55.recipeapp.models.*;
import com.ttweb55.recipeapp.repository.RecipeRepository;
import com.ttweb55.recipeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe createNewRecipe(RecipeMinimum recipe, User currentUser) {

        Recipe newRecipe = new Recipe();
        newRecipe.setTitle(recipe.getTitle());
        newRecipe.setSource(recipe.getSource());
        newRecipe.setUser(currentUser);

        newRecipe.getCategories().clear();
        for (Long categoryid : recipe.getCategories()) {
            Category addCategory = categoryService.findByCategoryId(categoryid);
            newRecipe.getCategories().add(new RecipeCategory(newRecipe, addCategory));
        }

        newRecipe.getIngredients().clear();
        for (String ingredient : recipe.getIngredients()) {
            Ingredient addIngredient = new Ingredient();
            addIngredient.setIngredientname(ingredient);
            newRecipe.getIngredients().add(addIngredient);
        }

        newRecipe.getInstructions().clear();
        for (String instructions : recipe.getInstructions()) {
            Instructions addInstructions = new Instructions();
            addInstructions.setInstructionDetails(instructions);
            newRecipe.getInstructions().add(addInstructions);
        }

        return recipeRepository.save(newRecipe);
    }

    @Override
    public List<Recipe> findAllRecipesForCurrentUser(String username) {
        List<Recipe> recipes = new ArrayList<>();

        recipeRepository.findCurrentUserRecipes(username)
                .iterator()
                .forEachRemaining(recipes::add);

        return recipes;
    }

    @Override
    public Recipe findRecipeById(Long id) {
        return recipeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe with id '"
                + id + "' Not Found"));
    }
}
