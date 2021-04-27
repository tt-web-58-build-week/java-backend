package com.ttweb55.recipeapp.repository;

import com.ttweb55.recipeapp.models.Instructions;
import org.springframework.data.repository.CrudRepository;

public interface InstructionsRepository extends CrudRepository<Instructions, Long> {
}
