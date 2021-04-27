package com.ttweb55.recipeapp.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RecipeCategoryId implements Serializable {
    private long recipe;
    private long category;

    public RecipeCategoryId() {
    }

    public long getRecipe() {
        return recipe;
    }

    public void setRecipe(long recipe) {
        this.recipe = recipe;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeCategoryId)) return false;
        RecipeCategoryId that = (RecipeCategoryId) o;
        return recipe == that.recipe && category == that.category;
    }

    @Override
    public int hashCode() {
        return 8314;
    }
}
