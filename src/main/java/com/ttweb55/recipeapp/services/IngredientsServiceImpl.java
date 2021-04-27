package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.models.Ingredient;
import com.ttweb55.recipeapp.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "ingredientsService")
public class IngredientsServiceImpl implements IngredientsService {
    @Autowired
    IngredientsRepository ingredientsRepository;

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientsRepository.save(ingredient);
    }
}
