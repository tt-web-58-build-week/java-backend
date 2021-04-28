package com.ttweb55.recipeapp.repository;

import com.ttweb55.recipeapp.models.Recipe;
import com.ttweb55.recipeapp.models.RecipeCategory;
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

    @Query(value = "SELECT * " +
            "FROM recipecategories rc " +
            "LEFT JOIN recipes r " +
            "ON rc.recipeid = r.recipeid " +
            "WHERE rc.categoryid = :categoryid",
            nativeQuery = true)
    Iterable<RecipeCategory> findRecipesByCategoryId(@Param("categoryid") Long categoryid);
}
