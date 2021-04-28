package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.exceptions.ResourceNotFoundException;
import com.ttweb55.recipeapp.models.*;
import com.ttweb55.recipeapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private InstructionsRepository instructionsRepository;

    // Only for seed data for now!
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

    @Transactional
    @Override
    public void deleteRecipe(long id) {
        if (recipeRepository.findById(id)
                .isPresent())
        {
            recipeRepository.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Recipe id " + id + " Not Found!");
        }
    }
    @Transactional
    @Override
    public Recipe updateRecipe(RecipeMinimum recipe, long id) {
        Recipe currentRecipe = findRecipeById(id);

        if (recipe.getTitle() != null)
        {
            currentRecipe.setTitle(recipe.getTitle());
        }

        if (recipe.getSource() != null)
        {
            currentRecipe.setSource(recipe.getSource());
        }

        if (recipe.getCategories() != null) {
            currentRecipe.getCategories().clear();
            for (Long categoryId : recipe.getCategories()) {
                Category addCategory = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new EntityNotFoundException("Category Id " + categoryId + " Not found!"));
                currentRecipe.getCategories()
                        .add(new RecipeCategory(currentRecipe, addCategory));
            }
        }

        if (recipe.getIngredients() != null) {
            currentRecipe.getIngredients().clear();
            for (String i : recipe.getIngredients()) {
                Ingredient addIngredient = new Ingredient();
                addIngredient.setIngredientname(i);
                currentRecipe.getIngredients().add(addIngredient);
            }
        }

        if (recipe.getInstructions() != null) {
            currentRecipe.getInstructions().clear();
            for (String inst : recipe.getInstructions()) {
                Instructions addInstructions = new Instructions();
                addInstructions.setInstructionDetails(inst);
                currentRecipe.getInstructions().add(addInstructions);
            }
        }

        return recipeRepository.save(currentRecipe);
    }
}
