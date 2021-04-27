package com.ttweb55.recipeapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryid;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "category", allowSetters = true)
    private Set<RecipeCategory> recipes = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RecipeCategory> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeCategory> recipes) {
        this.recipes = recipes;
    }
}
