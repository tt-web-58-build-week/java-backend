package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.models.Category;

import java.util.List;

public interface CategoryService {
    Category save(Category category);

    Category findByCategoryId(Long id);

    List<Category> findAllCategories();
}
