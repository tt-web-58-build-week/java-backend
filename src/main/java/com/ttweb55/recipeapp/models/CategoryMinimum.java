package com.ttweb55.recipeapp.models;

public class CategoryMinimum {
    private String name;
    private long categoryid;

    public CategoryMinimum(String name, long categoryid) {
        this.name = name;
        this.categoryid = categoryid;
    }

    public CategoryMinimum(Category category) {
        this.categoryid = category.getCategoryid();
        this.name = category.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }
}
