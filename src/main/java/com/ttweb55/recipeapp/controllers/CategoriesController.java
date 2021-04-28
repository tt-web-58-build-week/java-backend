package com.ttweb55.recipeapp.controllers;

import com.ttweb55.recipeapp.models.Category;
import com.ttweb55.recipeapp.models.CategoryMinimum;
import com.ttweb55.recipeapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllCategories() {
        List<CategoryMinimum> categories = categoryService.findAllCategories()
                .stream().map(CategoryMinimum::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
