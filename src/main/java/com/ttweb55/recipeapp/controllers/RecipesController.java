package com.ttweb55.recipeapp.controllers;

import com.ttweb55.recipeapp.models.Recipe;
import com.ttweb55.recipeapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RecipesController {
    @Autowired
    RecipeService recipeService;

    @GetMapping("/recipes")
    public ResponseEntity<?> getAllRecipes() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Recipe> recipeList = recipeService.findAllRecipesForCurrentUser(username);

        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }
}
