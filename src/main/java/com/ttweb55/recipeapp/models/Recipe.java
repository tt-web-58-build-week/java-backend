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

    @Column(nullable = false)
    private String title;

    private String source;

    @OneToMany(mappedBy = "recipe",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private Set<RecipeCategory> categories = new HashSet<>();

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

    @ManyToOne()
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "recipes", allowSetters = true)
    private User user;

    public Recipe()
    {
    }

    public Recipe(
            String title,
            String source,
            User user
            /*, List<Long> categories, List<String> instructions, List<String> ingredients*/)
    {
        this.title = title;
        this.source = source;
        this.user = user;
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

    public Set<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<RecipeCategory> categories) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
