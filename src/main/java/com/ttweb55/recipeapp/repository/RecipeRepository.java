package com.ttweb55.recipeapp.repository;

import com.ttweb55.recipeapp.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
