package com.example.rootsquad.backend.controller;

import com.example.rootsquad.backend.exception.ResourceNotFoundException;
import com.example.rootsquad.backend.model.Category;
import com.example.rootsquad.backend.service.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/public/api/category")
public class PublicCategoryController {
    @Autowired
    CategoryServiceInterface categoryService;

    // getting all categories
    @GetMapping
    public ResponseEntity<?> getCategories() {
        List<Category> categories = categoryService.findAll();

        if(categories.isEmpty())
            throw new ResourceNotFoundException();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // getting category by categoryId
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(category, HttpStatus.OK);
    }


}
