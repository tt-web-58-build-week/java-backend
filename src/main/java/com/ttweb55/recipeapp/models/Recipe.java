package com.ttweb55.recipeapp.models;


import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;

    private String title;
    private String source;

//    private Category category;

//    private Ingredient ingredient;

//    private Instructions instructions;

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

//    public Category getCategory()
//    {
//        return category;
//    }
//
//    public void setCategory(Category category)
//    {
//        this.category = category;
//    }
//
//    public Ingredient getIngredient()
//    {
//        return ingredient;
//    }
//
//    public void setIngredient(Ingredient ingredient)
//    {
//        this.ingredient = ingredient;
//    }
//
//    public Instructions getInstructions()
//    {
//        return instructions;
//    }
//
//    public void setInstructions(Instructions instructions)
//    {
//        this.instructions = instructions;
//    }
}
