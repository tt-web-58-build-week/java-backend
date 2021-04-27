package com.ttweb55.recipeapp.repository;

import com.ttweb55.recipeapp.models.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    @Query(value = "SELECT * " +
            "FROM recipes r " +
            "LEFT JOIN users u " +
            "ON r.userid = u.userid " +
            "WHERE u.username = :username",
        nativeQuery = true)
    Iterable<Recipe> findCurrentUserRecipes(@Param("username") String username);
}
