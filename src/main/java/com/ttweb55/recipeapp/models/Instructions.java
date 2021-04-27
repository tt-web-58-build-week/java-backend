package com.ttweb55.recipeapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "instructions")
public class Instructions extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long instructionsid;

    @Column(nullable = false)
    private String instructionDetails;

    @ManyToOne
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = "instructions", allowSetters = true)
    private Recipe recipe;

    public Instructions() {
    }

    public Instructions(String instructionDetails) {
        this.instructionDetails = instructionDetails;
    }

    public long getInstructionsid() {
        return instructionsid;
    }

    public void setInstructionsid(long instructionsid) {
        this.instructionsid = instructionsid;
    }

    public String getInstructionDetails() {
        return instructionDetails;
    }

    public void setInstructionDetails(String instructionDetails) {
        this.instructionDetails = instructionDetails;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
