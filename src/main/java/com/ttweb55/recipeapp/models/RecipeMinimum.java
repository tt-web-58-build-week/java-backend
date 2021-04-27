package com.ttweb55.recipeapp.models;

import java.util.List;

public class RecipeMinimum {
    private String title;
    private String source;

    private List<Long> categories;

    private List<String> instructions;

    private List<String> ingredients;

    public RecipeMinimum() {
    }

    public RecipeMinimum(String title, String source, List<Long> categories, List<String> instructions, List<String> ingredients) {
        this.title = title;
        this.source = source;
        this.categories = categories;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
