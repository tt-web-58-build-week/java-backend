package com.ttweb55.recipeapp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;

    private String title;
    private String source;

//    So Recipe - Many to many with Category
//One to many Ingredients
//One to many Instructions
    //    private Category category;
    @ManyToMany()
    @JoinTable(name = "recipecategories",
        joinColumns = @JoinColumn(name = "recipeid"),
        inverseJoinColumns = @JoinColumn(name = "categoryid")
    )
    @JsonIgnoreProperties(value = "recipes", allowSetters = true)
    private Set<Category> categories = new HashSet<>();


//    private Ingredient ingredient;
    @OneToMany(mappedBy = "recipe",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private List<Ingredient> ingredients = new ArrayList<>();

//    private Instructions instructions;
    @OneToMany(mappedBy = "recipe",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private List<Instructions> instructions = new ArrayList<>();

    public Recipe()
    {
    }

    public Recipe(String title, String source/*, Category category, Ingredient ingredient, Instructions instructions*/)
    {
        this.title = title;
        this.source = source;
//        this.category = category;
//        this.ingredient = ingredient;
//        this.instructions = instructions;
    }

    public long getRecipeid()
    {
        return recipeid;
    }

    public void setRecipeid(long recipeid)
    {
        this.recipeid = recipeid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instructions> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instructions> instructions) {
        this.instructions = instructions;
    }
}
