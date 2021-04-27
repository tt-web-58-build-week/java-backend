package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.models.Instructions;
import com.ttweb55.recipeapp.repository.InstructionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "instructionsService")
public class InstructionsServiceImpl implements InstructionsService {
    @Autowired
    private InstructionsRepository instructionsRepository;

    @Override
    public Instructions save(Instructions instructions) {
        return instructionsRepository.save(instructions);
    }
}
