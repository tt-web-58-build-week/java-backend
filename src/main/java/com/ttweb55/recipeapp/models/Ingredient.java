package com.ttweb55.recipeapp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientid;

    @Column(nullable = false)
    private String ingredientname;

    @ManyToOne
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = "ingredients", allowSetters = true)
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String ingredientname) {
        this.ingredientname = ingredientname;
    }

    public long getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(long ingredientid) {
        this.ingredientid = ingredientid;
    }

    public String getIngredientname() {
        return ingredientname;
    }

    public void setIngredientname(String ingredientname) {
        this.ingredientname = ingredientname;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
