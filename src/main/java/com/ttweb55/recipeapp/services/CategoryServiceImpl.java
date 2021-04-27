package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.exceptions.ResourceNotFoundException;
import com.ttweb55.recipeapp.models.Category;
import com.ttweb55.recipeapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findByCategoryId(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id '" + id + "' Not found"));
    }
}
