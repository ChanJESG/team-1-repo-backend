package com.example.rootsquad.backend.service;

import com.example.rootsquad.backend.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceInterface {
    public Category save(Category category);
    public List<Category> findAll();
    public Category update(Category category);
    public void delete(Long id);
    public Optional<Category> findById(Long id);
}
