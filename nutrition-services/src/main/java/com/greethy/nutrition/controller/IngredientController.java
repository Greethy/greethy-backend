package com.greethy.nutrition.controller;

import com.greethy.nutrition.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/ingredient/")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    @GetMapping()
    public ResponseEntity<?> findAllIngredients(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ingredientRepository.findAll());
    }

}
