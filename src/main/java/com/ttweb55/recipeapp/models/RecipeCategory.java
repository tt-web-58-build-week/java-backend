package com.ttweb55.recipeapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "recipecategories")
@IdClass(RecipeCategoryId.class)
public class RecipeCategory extends Auditable implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = "categories")
    private Recipe recipe;

    @Id
    @ManyToOne
    @JoinColumn(name = "categoryid")
    @JsonIgnoreProperties(value = "recipes")
    private  Category category;

    public RecipeCategory() {
    }

    public RecipeCategory(Recipe recipe, Category category) {
        this.recipe = recipe;
        this.category = category;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeCategory)) return false;

        RecipeCategory that = (RecipeCategory) o;
        return ((recipe == null ? 0 : recipe.getRecipeid()) == (that.recipe == null ? 0 : that.recipe.getRecipeid())) &&
                ((category == null ? 0 : category.getCategoryid()) == (that.category == null ? 0 : that.category.getCategoryid()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, category);
    }
}
