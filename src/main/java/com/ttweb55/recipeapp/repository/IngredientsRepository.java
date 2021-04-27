package com.ttweb55.recipeapp.repository;

import com.ttweb55.recipeapp.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientsRepository extends CrudRepository<Ingredient, Long> {
}
