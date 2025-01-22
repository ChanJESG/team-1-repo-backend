package com.example.rootsquad.backend.controller;

import com.example.rootsquad.backend.exception.ResourceNotFoundException;
import com.example.rootsquad.backend.model.Category;
import com.example.rootsquad.backend.service.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api/category")
public class AdminCategoryController {
    @Autowired
    CategoryServiceInterface categoryService;

    // adding new category
    @PostMapping
    public ResponseEntity<Category> addCategory (@RequestBody Category category) {
        Category savedCategory = categoryService.save(category);

        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // updating category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory (@PathVariable("id") Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.findById(id).map(foundCategory -> {
            foundCategory.setName(category.getName());

            return categoryService.save(foundCategory);
        }).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // deleting category by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable("id") Long id) {
        Category deletedCategory = categoryService.findById(id).map(foundCategory-> {
            categoryService.delete(id);

            return foundCategory;
        }).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }

}
