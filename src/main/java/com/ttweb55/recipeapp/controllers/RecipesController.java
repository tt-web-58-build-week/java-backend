package com.ttweb55.recipeapp.controllers;

import com.ttweb55.recipeapp.models.Recipe;
import com.ttweb55.recipeapp.models.RecipeMinimum;
import com.ttweb55.recipeapp.models.User;
import com.ttweb55.recipeapp.services.RecipeService;
import com.ttweb55.recipeapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/recipes")
public class RecipesController {
    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllRecipes() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Recipe> recipeList = recipeService.findAllRecipesForCurrentUser(username);

        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<?> getRecipeById(@PathVariable Long recipeId) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<?> createNewRecipe(
            @Valid
            @RequestBody
            RecipeMinimum recipeMinimum
    ) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        User currentUser = userService.findByName(username);

        Recipe newRecipe = recipeService.createNewRecipe(recipeMinimum, currentUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRecipeURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/recipe/{recipeId}")
                .buildAndExpand(newRecipe.getRecipeid())
                .toUri();
        responseHeaders.setLocation(newRecipeURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/recipe/{recipeId}", consumes = "application/json")
    public ResponseEntity<?> updateRecipe(
            @Valid
            @RequestBody
                    RecipeMinimum updateRecipe,
            @PathVariable
                    long recipeId)
    {
        recipeService.updateRecipe(updateRecipe, recipeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/recipe/{id}")
    public ResponseEntity<?> deleteRecipeById(
            @PathVariable long id)
    {
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
