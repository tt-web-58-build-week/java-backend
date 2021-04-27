package com.ttweb55.recipeapp.repository;

import com.ttweb55.recipeapp.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
